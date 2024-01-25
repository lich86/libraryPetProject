package com.chervonnaya.library.config;

import com.chervonnaya.library.dto.BookDTO;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.model.Book;
import com.chervonnaya.library.service.AuthorService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookMapperConfigurer implements BaseMapConfigurer {

    @Autowired
    AuthorService authorService;

    public void configure(ModelMapper mapper) {
        Converter<List<Long>, List<Author>> converter = context -> {
            List<Author> authors;
            authors = context.getSource().stream()
                    .map(id -> authorService.getById(id))
                    .toList();
            return authors;
        };
        PropertyMap<BookDTO, Book> map = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(converter).map(source.getAuthorIds(), destination.getAuthors());
            }
        };

        mapper.addMappings(map);
    }
}
