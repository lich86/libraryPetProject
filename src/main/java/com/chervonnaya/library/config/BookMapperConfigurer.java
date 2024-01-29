package com.chervonnaya.library.config;

import com.chervonnaya.library.dto.BookDTO;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.model.Book;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapperConfigurer implements BaseMapConfigurer {

    public void configure(ModelMapper mapper) {
        Converter<List<Long>, List<Author>> converter = context -> {
            List<Author> authors = context.getSource().stream()
                    .map(id -> {
                        Author author = new Author();
                        author.setId(id);
                        return author;
                    }).toList();
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
