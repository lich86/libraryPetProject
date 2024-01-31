package com.chervonnaya.library.controller;

import com.chervonnaya.library.dto.AuthorDTO;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.model.Book;
import com.chervonnaya.library.service.AuthorService;
import com.chervonnaya.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Page<Author> getAuthors(Pageable pageable) {
        return authorService.findAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author getAuthor(@PathVariable(name = "id") Long id) {
        return authorService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        return authorService.save(authorDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Author updateAuthor(@PathVariable(name = "id") Long id,
            @Valid @RequestBody AuthorDTO authorDTO) {
        return authorService.update(id, authorDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAuthor(@PathVariable(name = "id") Long id) {
        authorService.delete(id);
    }

    @RequestMapping(value = "/{id}/book", method = RequestMethod.GET)
    public Page<Book> getAllByBooksAuthor(Pageable pageable,
                               @PathVariable(name = "id") Long id) {
        return bookService.getAllByAuthor(id, pageable);
    }

}
