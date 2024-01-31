package com.chervonnaya.library.controller;

import com.chervonnaya.library.dto.CopyDTO;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.service.CopyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/copy")
public class CopyController {
    private final CopyService copyService;

    @Autowired
    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Copy> getCopies(Pageable pageable) {
        return copyService.findAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Copy getCopy(@PathVariable(name = "id") Long id) {
        return copyService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Copy addCopy(@Valid @RequestBody CopyDTO copyDTO) {
        return copyService.save(copyDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Copy updateCopy(@PathVariable(name = "id") Long id,
                           @Valid @RequestBody CopyDTO copyDTO) {
        return copyService.update(id, copyDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCopy(@PathVariable(name = "id") Long id) {
        copyService.delete(id);
    }


}
