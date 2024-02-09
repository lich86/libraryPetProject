package com.chervonnaya.library.service;

import com.chervonnaya.library.dto.CopyDTO;
import com.chervonnaya.library.model.Copy;

public interface CopyService extends CrudService<Copy, CopyDTO> {
    boolean copyHasNullReturnDate(Long copyId);
}
