package com.chervonnaya.library.config;

import com.chervonnaya.library.dto.ReaderDTO;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Reader;
import com.chervonnaya.library.service.CopyService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReaderMapperConfigurer implements BaseMapConfigurer{
    private CopyService copyService;

    public void setCopyService(@Autowired CopyService copyService) {
        this.copyService = copyService;
    }

    @Override
    public void configure(ModelMapper mapper) {
        Converter<Long, List<Copy>> converter = context -> {
            Copy copy = copyService.getById(context.getSource());
            List<Copy> copies = copyService.findAll();
            copies.add(copy);
            return copies;
        };

        PropertyMap<ReaderDTO, Reader> map = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(converter).map(source.getCopyId(), destination.getCopies());
            }
        };

        mapper.addMappings(map);
    }
}
