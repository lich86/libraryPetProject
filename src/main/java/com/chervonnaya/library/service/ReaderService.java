package com.chervonnaya.library.service;

import com.chervonnaya.library.dto.ReaderDTO;
import com.chervonnaya.library.model.Reader;

import java.util.List;

public interface ReaderService extends CrudService<Reader, ReaderDTO> {
    Reader patch(Long id, List<Long> readerIds);
}
