package com.chervonnaya.library.config.configurers;

import com.chervonnaya.library.dto.BookDTO;
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
public class BookMapperConfigurer implements BaseMapConfigurer {

    public void configure(ModelMapper mapper) {
        Converter<List<Long>, Set<Author>> converter = context -> {
            if(isNull(context.getSource())) {
                return null;
            }
            Set<Author> authors = context.getSource().stream()
                    .map(id -> {
                        Author author = new Author();
                        author.setId(id);
                        return author;
                    }).collect(Collectors.toCollection(HashSet::new));
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
