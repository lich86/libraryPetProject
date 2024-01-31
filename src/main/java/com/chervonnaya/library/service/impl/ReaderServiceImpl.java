package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.ReaderDTO;
import com.chervonnaya.library.exception.EntityNotFoundException;
import com.chervonnaya.library.exception.SaveEntityException;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Reader;
import com.chervonnaya.library.repository.ReaderRepository;
import com.chervonnaya.library.service.ReaderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReaderServiceImpl extends CrudServiceImpl<Reader, ReaderDTO, ReaderRepository> implements ReaderService {
    public ReaderServiceImpl(ReaderRepository repository, ModelMapper mapper) {
        super(repository, Reader.class, mapper);
    }

    @Transactional
    public Reader patch(Long id, List<Long> readerIds) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reader"));
        try {
            Reader reader = this.getById(id);
            List<Copy> copies = readerIds.stream()
                    .map(copyId -> {
                        Copy copy = new Copy();
                        copy.setId(copyId);
                        return copy;
                    }).collect(Collectors.toCollection(ArrayList::new));
            reader.setCopies(copies);
            repository.save(reader);
            log.info("Reader update, id: " + id);
            return reader;
        } catch (Exception ex) {
            log.error("Reader don't update, id: " + id);
            throw new SaveEntityException("Reader");
        }
    }
}
