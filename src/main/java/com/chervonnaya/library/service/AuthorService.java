package com.chervonnaya.library.service;

import com.chervonnaya.library.dto.AuthorDTO;
import com.chervonnaya.library.model.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService extends CrudService<Author, AuthorDTO> {
}
