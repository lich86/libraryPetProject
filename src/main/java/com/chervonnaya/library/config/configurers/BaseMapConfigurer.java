package com.chervonnaya.library.config.configurers;

import org.modelmapper.ModelMapper;

public interface BaseMapConfigurer {
    void configure(ModelMapper mapper);
}
