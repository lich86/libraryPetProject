package com.chervonnaya.library.config;

import com.chervonnaya.library.config.configurers.AuthorMapperConfigurer;
import com.chervonnaya.library.config.configurers.BookMapperConfigurer;
import com.chervonnaya.library.config.configurers.CopyMapperConfigurer;
import com.chervonnaya.library.config.configurers.UserMapperConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class MapperConfig {

    private final AuthorMapperConfigurer authorMapperConfigurer;
    private final BookMapperConfigurer bookMapperConfigurer;
    private final CopyMapperConfigurer copyMapperConfigurer;
    private final UserMapperConfigurer userMapperConfigurer;

    @Autowired
    public MapperConfig(AuthorMapperConfigurer authorMapperConfigurer, BookMapperConfigurer bookMapperConfigurer, CopyMapperConfigurer copyMapperConfigurer, UserMapperConfigurer userMapperConfigurer) {
        this.authorMapperConfigurer = authorMapperConfigurer;
        this.bookMapperConfigurer = bookMapperConfigurer;
        this.copyMapperConfigurer = copyMapperConfigurer;
        this.userMapperConfigurer = userMapperConfigurer;
    }


    @Bean
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        authorMapperConfigurer.configure(modelMapper);
        bookMapperConfigurer.configure(modelMapper);
        copyMapperConfigurer.configure(modelMapper);
        userMapperConfigurer.configure(modelMapper);
        return modelMapper;
    }
}
