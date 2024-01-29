package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.ReaderCopyDTO;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Reader;
import com.chervonnaya.library.repository.ReaderRepository;
import com.chervonnaya.library.service.ReaderCopyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ReaderCopyServiceImpl extends CrudServiceImpl<Reader, ReaderCopyDTO, ReaderRepository> implements ReaderCopyService {
    public ReaderCopyServiceImpl(ReaderRepository repository, ModelMapper mapper) {
        super(repository, Reader.class, mapper);
    }

    @Transactional
    @Override
    public Reader update(Long id, ReaderCopyDTO readerCopyDTO) {
        repository.findById(id).orElseThrow(RuntimeException::new); //TODO
        try {
            Reader reader = repository.getReferenceById(id);
            List<Copy> copies = mapper.map(readerCopyDTO, Reader.class).getCopies();
            reader.setCopies(copies);
            log.info("Reader update, id: " + id);
            return reader;
        } catch (Exception ex) {
            log.error(this.genericType.getSimpleName() + " don't update, id: " + id);
            throw new RuntimeException(); //TODO
        }

    }
}
