package com.chervonnaya.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class MapperConfig {

    private final AuthorMapperConfigurer authorMapperConfigurer;
    private final BookMapperConfigurer bookMapperConfigurer;
    private final CopyMapperConfigurer copyMapperConfigurer;
    private final ReaderMapperConfigurer readerMapperConfigurer;

    @Autowired
    public MapperConfig(AuthorMapperConfigurer authorMapperConfigurer, BookMapperConfigurer bookMapperConfigurer, CopyMapperConfigurer copyMapperConfigurer, ReaderMapperConfigurer readerMapperConfigurer) {
        this.authorMapperConfigurer = authorMapperConfigurer;
        this.bookMapperConfigurer = bookMapperConfigurer;
        this.copyMapperConfigurer = copyMapperConfigurer;
        this.readerMapperConfigurer = readerMapperConfigurer;
    }


    @Bean
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        authorMapperConfigurer.configure(modelMapper);
        bookMapperConfigurer.configure(modelMapper);
        copyMapperConfigurer.configure(modelMapper);
        readerMapperConfigurer.configure(modelMapper);
        return modelMapper;
    }
}
