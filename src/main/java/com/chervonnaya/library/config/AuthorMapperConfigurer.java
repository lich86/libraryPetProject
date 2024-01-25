package com.chervonnaya.library.config;

import com.chervonnaya.library.dto.AuthorDTO;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.model.Book;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;

public class AuthorMapperConfigurer implements BaseMapConfigurer{
    public void configure(ModelMapper mapper) {
        Converter<List<String>, List<Book>> converter = context -> {
            List<Book> books;
            books = context.getSource().stream()
                    .map(title -> {
                        Book book = new Book();
                        book.setTitle(title);
                        return book;
                    })
                    .toList();

            return books;
        };
        PropertyMap<AuthorDTO, Author> map = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(converter).map(source.getBookTitles(), destination.getBooks());
            }
        };

        mapper.addMappings(map);
    }

}
