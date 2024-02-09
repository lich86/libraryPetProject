package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.ReaderDTO;
import com.chervonnaya.library.model.Reader;
import com.chervonnaya.library.repository.ReaderRepository;
import com.chervonnaya.library.service.ReaderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReaderServiceImpl extends CrudServiceImpl<Reader, ReaderDTO, ReaderRepository> implements ReaderService {
    public ReaderServiceImpl(ReaderRepository repository, ModelMapper mapper) {
        super(repository, Reader.class, mapper);
    }
}
