package com.chervonnaya.library.config;

import org.modelmapper.ModelMapper;

public interface BaseMapConfigurer {
    void configure(ModelMapper mapper);
}
