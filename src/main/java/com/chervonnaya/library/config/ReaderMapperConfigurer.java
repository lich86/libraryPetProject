package com.chervonnaya.library.config;

import com.chervonnaya.library.dto.ReaderDTO;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Reader;
import com.chervonnaya.library.repository.CopyRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReaderMapperConfigurer implements BaseMapConfigurer{
    @Override
    public void configure(ModelMapper mapper) {
        Converter<List<Long>, List<Copy>> converter = context -> {
            List<Copy> copies = context.getSource().stream()
                    .map(id -> {
                        Copy copy = new Copy();
                        copy.setId(id);
                        return copy;
                    }).toList();
            return copies;
        };

        PropertyMap<ReaderDTO, Reader> map = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(converter).map(source.getCopyIds(), destination.getCopies());
            }
        };

        mapper.addMappings(map);
    }
}
