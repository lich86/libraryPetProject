package com.chervonnaya.library.controller;

import com.chervonnaya.library.dto.ReaderCopyDTO;
import com.chervonnaya.library.dto.ReaderDTO;
import com.chervonnaya.library.model.Reader;
import com.chervonnaya.library.service.ReaderCopyService;
import com.chervonnaya.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reader")
public class ReaderController {

    private final ReaderService readerService;
    private final ReaderCopyService readerCopyService;


    public ReaderController(@Autowired ReaderService readerService, ReaderCopyService readerCopyService) {
        this.readerService = readerService;
        this.readerCopyService = readerCopyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Reader> getReaders(Pageable pageable) {
        return readerService.findAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Reader getReader(@PathVariable(name = "id") Long id) {
        return readerService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Reader addReader(@Valid @RequestBody ReaderDTO readerDTO) {
        return readerService.save(readerDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Reader updateReader(@PathVariable(name = "id") Long id,
                               @Valid @RequestBody ReaderDTO readerDTO) {
        return readerService.update(id, readerDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteReader(@PathVariable(name = "id") Long id) {
        readerService.delete(id);
    }

    @RequestMapping(value = "/{id}/copies", method = RequestMethod.PUT)
    public Reader editReaderCopies(@PathVariable(name = "id") Long id,
                                @Valid @RequestBody ReaderCopyDTO readerCopyDTO){
        return readerCopyService.update(id,readerCopyDTO);
    }
}
