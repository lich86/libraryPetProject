package com.chervonnaya.library.config;

import com.chervonnaya.library.dto.CopyDTO;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Reader;
import com.chervonnaya.library.service.ReaderService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CopyMapperConfigurer implements BaseMapConfigurer{

    private ReaderService readerService;

    public void setReaderService(@Autowired ReaderService readerService) {
        this.readerService = readerService;
    }

    @Override
    public void configure(ModelMapper mapper) {
        Converter<Long, List<Reader>> converter = context -> {
            Reader reader = readerService.getById(context.getSource());
            List<Reader> readers = readerService.findAll();
            readers.add(reader);
            return readers;
        };

        PropertyMap<CopyDTO, Copy> map = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(converter).map(source.getReaderId(), destination.getReaders());
            }
        };

    }
}
