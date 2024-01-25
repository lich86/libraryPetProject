package com.chervonnaya.library.service;

import com.chervonnaya.library.dto.BaseDTO;
import com.chervonnaya.library.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService <E extends BaseEntity, D extends BaseDTO> {
    E getById(Long id);

    E save(D d);

    void delete(Long id);

    E update(Long id, D d);

    Page<E> findAll(Pageable pageable);

    List<E> findAll();

}
