package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.BookDTO;
import com.chervonnaya.library.exception.EntityNotFoundException;
import com.chervonnaya.library.exception.SaveEntityException;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.model.Book;
import com.chervonnaya.library.repository.BookRepository;
import com.chervonnaya.library.service.AuthorService;
import com.chervonnaya.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl extends CrudServiceImpl<Book, BookDTO, BookRepository> implements BookService {

    public final AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository repository, ModelMapper mapper, AuthorService authorService) {
        super(repository, Book.class, mapper);
        this.authorService = authorService;
    }

    @Transactional
    public Book patch(Long id, List<Long> authorIds) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book"));
        try {
            Book book = this.getById(id);
            Set<Author> authors = authorIds.stream()
                    .map(authorId ->{
                        Author author = authorService.getById(authorId);
                        return author;
                    }).collect(Collectors.toCollection(HashSet::new));
            book.setAuthors(authors);
            repository.save(book);
            log.info("Book update, id: " + id);
            return book;
        } catch (Exception ex) {
            log.error("Book don't update, id: " + id);
            throw new SaveEntityException("Book");
        }
    }

    public Book patch(Long id, Long authorId) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book"));
        try {
            Book book = this.getById(id);
            Set<Author> authors = book.getAuthors();
            Author author = authorService.getById(authorId);
            authors.add(author);
            book.setAuthors(authors);
            repository.save(book);
            log.info("Book update, id: " + id);
            return book;
        } catch (Exception ex) {
            log.error("Book don't update, id: " + id);
            throw new SaveEntityException("Book");
        }
    }

    public Page<Book> getAllByAuthor(long authorId, Pageable pageable) {
        return repository.getAllByAuthor(authorId, pageable);
    }

    public Set<Book> getAllByAuthor(long authorId) {
        return repository.getAllByAuthor(authorId);
    }
}
