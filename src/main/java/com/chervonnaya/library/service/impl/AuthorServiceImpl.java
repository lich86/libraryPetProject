package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.AuthorDTO;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.repository.AuthorRepository;
import com.chervonnaya.library.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorServiceImpl extends CrudServiceImpl<Author, AuthorDTO, AuthorRepository> implements AuthorService {
    @Autowired
    public AuthorServiceImpl(AuthorRepository repository, ModelMapper mapper) {
        super(repository, Author.class, mapper);
    }

}
