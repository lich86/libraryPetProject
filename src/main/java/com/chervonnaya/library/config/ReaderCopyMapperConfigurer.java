package com.chervonnaya.library.config;

import com.chervonnaya.library.dto.ReaderCopyDTO;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Reader;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReaderCopyMapperConfigurer implements BaseMapConfigurer{
    @Override
    public void configure(ModelMapper mapper) {
        Converter<List<Long>, List<Copy>> readerConverter = context -> {
            try {
                List<Copy> copies = context.getSource().stream()
                        .map(id -> {
                            Copy copy = new Copy();
                            copy.setId(id);
                            return copy;
                        }).toList();
                return copies;
            } catch (Exception ex) {
                return null;
            }
        };

        PropertyMap<ReaderCopyDTO, Reader> map = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(readerConverter).map(source.getCopyIds(), destination.getCopies());
            }
        };

        mapper.addMappings(map);

    }
}
