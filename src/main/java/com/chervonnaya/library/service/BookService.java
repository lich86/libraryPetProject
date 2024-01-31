package com.chervonnaya.library.service;

import com.chervonnaya.library.dto.BookDTO;
import com.chervonnaya.library.model.Book;

import java.util.List;

public interface BookService extends CrudService<Book, BookDTO> {
    Book patch(Long id, List<Long> authorIds);
}
