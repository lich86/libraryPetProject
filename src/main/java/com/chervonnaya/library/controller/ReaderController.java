package com.chervonnaya.library.controller;

import com.chervonnaya.library.dto.ReaderDTO;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.model.Reader;
import com.chervonnaya.library.service.CopyService;
import com.chervonnaya.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reader")
public class ReaderController {

    private final ReaderService readerService;
    private final CopyService copyService;

    @Autowired
    public ReaderController(ReaderService readerService, CopyService copyService) {
        this.readerService = readerService;
        this.copyService = copyService;
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


    @RequestMapping(value = "/{id}/copy", method = RequestMethod.GET)
    public Page<Copy> getReaderCopies(Pageable pageable,
                                      @PathVariable(name = "id") Long id) {
        return copyService.getAllByReader(id, pageable);
    }

    @RequestMapping(value = "/{id}/copy", method = RequestMethod.PATCH)
    public Reader editReaderCopies(@PathVariable(name = "id") Long id,
                                   @Valid @RequestBody List<Long> readerIds){
        return readerService.patch(id, readerIds);
    }
}
