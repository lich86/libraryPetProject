package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.BaseDTO;
import com.chervonnaya.library.exception.EntityNotFoundException;
import com.chervonnaya.library.exception.SaveEntityException;
import com.chervonnaya.library.model.BaseEntity;
import com.chervonnaya.library.repository.IRepository;
import com.chervonnaya.library.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class CrudServiceImpl<E extends BaseEntity, D extends BaseDTO, R extends IRepository<E>> implements CrudService<E, D> {
    final R repository;
    final Class<E> genericType;
    final ModelMapper mapper;

    public CrudServiceImpl(R repository, Class<E> genericType, ModelMapper mapper) {
        this.repository = repository;
        this.genericType = genericType;
        this.mapper = mapper;
    }

    @Override
    public E getById(Long id) {
        Optional<E> entity = repository.findById(id);
        if(entity.isPresent()) {
            log.info(this.genericType.getSimpleName() + " get, id: " + id);
            return entity.get();
        }
        log.error(this.genericType.getSimpleName() + " don't get, id: " + id);
        throw new EntityNotFoundException(this.genericType.getSimpleName());
    }

    @Transactional
    @Override
    public E save(D d) {
        try {
            E entity = repository.save(mapper.map(d, genericType));
            log.info(this.genericType.getSimpleName() + " save, id: " + entity.getId());
            return entity;
        } catch (Exception ex) {
            log.error(this.genericType.getSimpleName() + " don't save");
            //throw new SaveEntityException(this.genericType.getSimpleName());
            throw new RuntimeException(ex);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
            log.info(this.genericType.getSimpleName() + " delete, id: " + id);
        } catch (Exception ex) {
            log.error(this.genericType.getSimpleName() + " don't delete, id: " + id
                    + " Error message: " + ex.getMessage());
        }
    }

    @Transactional
    @Override
    public E update(Long id, D d) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException(this.genericType.getSimpleName()));
        try {
            d.setId(id);
            E entity = repository.save(mapper.map(d, genericType));
            log.info(this.genericType.getSimpleName() + " update, id: " + id);
            return entity;
        } catch (Exception ex) {
            log.error(this.genericType.getSimpleName() + " don't update, id: " + id);
            throw new SaveEntityException(this.genericType.getSimpleName());
        }

    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        Page<E> all = repository.findAll(pageable);
        log.info("Page " + this.genericType.getSimpleName() + " create");
        return all;
    }

    public List<E> findAll() {
        return repository.findAll();
    }


}
