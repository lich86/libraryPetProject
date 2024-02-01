package com.chervonnaya.library.config.configurers;

import com.chervonnaya.library.dto.AuthorDTO;
import com.chervonnaya.library.model.Author;
import com.chervonnaya.library.model.Book;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class AuthorMapperConfigurer implements BaseMapConfigurer{
    public void configure(ModelMapper mapper) {
        Converter<List<String>, Set<Book>> converter = context -> {
            if(isNull(context.getSource())) {
                return null;
            }
            Set<Book> books = context.getSource().stream()
                    .map(title -> {
                        Book book = new Book();
                        book.setOriginalTitle(title);
                        return book;
                    }).collect(Collectors.toCollection(HashSet::new));

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
