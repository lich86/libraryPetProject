package com.chervonnaya.library.config.configurers;

import com.chervonnaya.library.dto.CopyDTO;
import com.chervonnaya.library.model.Book;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Reader;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CopyMapperConfigurer implements BaseMapConfigurer{

    @Override
    public void configure(ModelMapper mapper) {
        Converter<List<Long>, List<Reader>> readerConverter = context -> {
            List<Reader> readers = context.getSource().stream()
                    .map(id -> {
                        Reader reader = new Reader();
                        reader.setId(id);
                        return reader;
                    }).toList();
            return readers;

        };
        Converter<Long, Book> bookConverter = context -> {
            Book book = new Book();
            book.setId(context.getSource());
            return book;
        };

        PropertyMap<CopyDTO, Copy> map = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(readerConverter).map(source.getReaderIds(), destination.getReaders());
                using(bookConverter).map(source.getBookId(),destination.getBook());
            }
        };

        mapper.addMappings(map);

    }
}
