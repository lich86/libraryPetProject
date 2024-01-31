package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.BookDTO;
import com.chervonnaya.library.exception.EntityNotFoundException;
import com.chervonnaya.library.exception.SaveEntityException;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.model.Book;
import com.chervonnaya.library.repository.BookRepository;
import com.chervonnaya.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl extends CrudServiceImpl<Book, BookDTO, BookRepository> implements BookService {
    public BookServiceImpl(BookRepository repository, ModelMapper mapper) {
        super(repository, Book.class, mapper);
    }

    @Transactional
    public Book patch(Long id, List<Long> authorIds) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book"));
        try {
            Book book = this.getById(id);
            List<Author> authors = authorIds.stream()
                    .map(authorId ->{
                        Author author = new Author();
                        author.setId(authorId);
                        return author;
                    }).collect(Collectors.toCollection(ArrayList::new));
            book.setAuthors(authors);
            repository.save(book);
            log.info("Book update, id: " + id);
            return book;
        } catch (Exception ex) {
            log.error("Book don't update, id: " + id);
            throw new SaveEntityException("Book");
        }
    }
}
