package com.chervonnaya.library.config.configurers;

import com.chervonnaya.library.dto.ReaderDTO;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Reader;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class ReaderMapperConfigurer implements BaseMapConfigurer{
    @Override
    public void configure(ModelMapper mapper) {
        Converter<List<Long>, List<Copy>> converter = context -> {
            if(isNull(context.getSource())) {
                return null;
            }
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
