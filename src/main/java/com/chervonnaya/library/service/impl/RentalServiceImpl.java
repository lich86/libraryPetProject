package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.RentalDTO;
import com.chervonnaya.library.exception.SaveEntityException;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Reader;
import com.chervonnaya.library.model.Rental;
import com.chervonnaya.library.repository.RentalRepository;
import com.chervonnaya.library.service.CopyService;
import com.chervonnaya.library.service.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.time.LocalDateTime.now;

@Service
@Slf4j
public class RentalServiceImpl extends CrudServiceImpl<Rental, RentalDTO, RentalRepository> implements RentalService {

    private final CopyService copyService;
    public RentalServiceImpl(RentalRepository repository, ModelMapper mapper, CopyService copyService) {
        super(repository, Rental.class, mapper);
        this.copyService = copyService;
    }

    public Set<Rental> saveRentals(RentalDTO rentalDTO) {
        try {
            Set<Long> copyIds = rentalDTO.getCopyIds();
            Set<Rental> rentals = new HashSet<>();
            Reader reader = new Reader();
            reader.setId(rentalDTO.getReaderId());
            for (Long copyId : copyIds) {
                try {
                    if(copyService.copyHasNullReturnDate(copyId)) {
                        log.error("Copy " + copyId + " is already taken");
                        throw new IllegalStateException("Cannot add a new rental for a copy with a null return date."); //TODO
                    }
                    Rental rental = new Rental();
                    Copy copy = new Copy();
                    copy.setId(copyId);
                    rental.setCopy(copy);
                    rental.setReader(reader);
                    repository.save(rental);
                    log.info("Rental save, id: " + rental.getId());
                    rentals.add(rental);
                } catch (Exception ex) {
                    log.error("Rental don't save");
                    throw new SaveEntityException("Rental");
                }
            }
            return rentals;
        }catch (Exception ex) {
            throw new RuntimeException(ex); //TODO
        }
    }

    public Rental returnCopy(Long id) {
        Rental rental = repository.findByCopyId(id);
        rental.setReturnDate(now());
        repository.save(rental);
        return rental;
    }


}
