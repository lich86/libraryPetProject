package com.chervonnaya.library.service;

import com.chervonnaya.library.dto.CopyDTO;
import com.chervonnaya.library.model.Copy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CopyService extends CrudService<Copy, CopyDTO> {
    Page<Copy> getAllByReader(long readerId, Pageable pageable);
}
