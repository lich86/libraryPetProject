package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.CopyDTO;
import com.chervonnaya.library.model.Copy;
import com.chervonnaya.library.repository.CopyRepository;
import com.chervonnaya.library.service.CopyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CopyServiceImpl extends CrudServiceImpl<Copy, CopyDTO, CopyRepository> implements CopyService {
    public CopyServiceImpl(CopyRepository repository, ModelMapper mapper) {
        super(repository, Copy.class, mapper);
    }
}
