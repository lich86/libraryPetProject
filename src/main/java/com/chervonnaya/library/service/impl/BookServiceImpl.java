package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.BookDTO;
import com.chervonnaya.library.model.Book;
import com.chervonnaya.library.repository.BookRepository;
import com.chervonnaya.library.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl extends CrudServiceImpl<Book, BookDTO, BookRepository> implements BookService {
    public BookServiceImpl(BookRepository repository, ModelMapper mapper) {
        super(repository, Book.class, mapper);
    }

/*    @Autowired
    private AuthorService authorService;

    @Override
    public Book save(BookDTO bookDTO) {
        List<Author> authors = bookDTO.getAuthorIds().stream()
                .map(id -> authorService.getById(id))
                .toList();
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(book.getDescription());
        book.setAuthors(authors);
        book.setGenres(bookDTO.getGenres());
        return repository.save(book);
    }*/
}
