package com.chervonnaya.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        new AuthorMapperConfigurer().configure(modelMapper);
        new BookMapperConfigurer().configure(modelMapper);
        new CopyMapperConfigurer().configure(modelMapper);
        return modelMapper;
    }
}
