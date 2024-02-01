package com.chervonnaya.library.service;

import com.chervonnaya.library.dto.BookDTO;
import com.chervonnaya.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface BookService extends CrudService<Book, BookDTO> {
    Book patch(Long id, List<Long> authorIds);
    Book patch(Long id, Long authorId);
    Page<Book> getAllByAuthor(long authorId, Pageable pageable);
    Set<Book> getAllByAuthor(long authorId);
}
