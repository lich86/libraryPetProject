package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.AuthorDTO;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.repository.AuthorRepository;
import com.chervonnaya.library.service.AuthorService;
import org.modelmapper.ModelMapper;

public class AuthorServiceImpl extends CrudServiceImpl<Author, AuthorDTO, AuthorRepository> implements AuthorService {
    public AuthorServiceImpl(AuthorRepository repository, ModelMapper mapper) {
        super(repository, Author.class, mapper);
    }
}
