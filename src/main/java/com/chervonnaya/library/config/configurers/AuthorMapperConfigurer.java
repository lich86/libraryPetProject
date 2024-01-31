package com.chervonnaya.library.config.configurers;

import com.chervonnaya.library.dto.AuthorDTO;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.model.Book;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class AuthorMapperConfigurer implements BaseMapConfigurer{
    public void configure(ModelMapper mapper) {
        Converter<List<String>, List<Book>> converter = context -> {
            if(isNull(context.getSource())) {
                return null;
            }
            List<Book> books = context.getSource().stream()
                    .map(title -> {
                        Book book = new Book();
                        book.setTitle(title);
                        return book;
                    }).toList();

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