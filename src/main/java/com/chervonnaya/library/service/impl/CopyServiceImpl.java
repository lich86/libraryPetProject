package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.CopyDTO;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Rental;
import com.chervonnaya.library.repository.CopyRepository;
import com.chervonnaya.library.service.CopyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CopyServiceImpl extends CrudServiceImpl<Copy, CopyDTO, CopyRepository> implements CopyService {
    public CopyServiceImpl(CopyRepository repository, ModelMapper mapper) {
        super(repository, Copy.class, mapper);
    }
    public boolean copyHasNullReturnDate(Long copyId) {
        Copy copy = this.getById(copyId);
        Set<Rental> rentals = copy.getRentals();
        return rentals.stream().anyMatch(rental -> rental.getReturnDate() == null);
    }
}
