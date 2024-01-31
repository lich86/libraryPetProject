package com.chervonnaya.library.controller;

import com.chervonnaya.library.dto.BookDTO;
import com.chervonnaya.library.model.Book;
import com.chervonnaya.library.service.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@Slf4j
public class BookController {
    private final BookService bookService;

    public BookController(@Autowired BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Book> getBooks(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable(name = "id") Long id) {
        return bookService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Book updateBook(@PathVariable(name = "id") Long id,
                               @Valid @RequestBody BookDTO bookDTO) {
        return bookService.update(id, bookDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable(name = "id") Long id) {
        bookService.delete(id);
    }

    @RequestMapping(value = "/{id}/author", method = RequestMethod.PATCH)
    public Book editBookAuthors(@PathVariable(name = "id") Long id,
                                @RequestBody List<Long> authorIds) {
        return bookService.patch(id, authorIds);
    }
}
