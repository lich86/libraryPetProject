package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.BookDTO;
import com.chervonnaya.library.model.Book;
import com.chervonnaya.library.repository.BookRepository;
import com.chervonnaya.library.service.BookService;
import org.modelmapper.ModelMapper;

public class BookServiceImpl extends CrudServiceImpl<Book, BookDTO, BookRepository> implements BookService {
    public BookServiceImpl(BookRepository repository, ModelMapper mapper) {
        super(repository, Book.class, mapper);
    }
}
