package com.chervonnaya.library.service.impl;

import com.chervonnaya.library.dto.UserDTO;
import com.chervonnaya.library.exception.EntityNotFoundException;
import com.chervonnaya.library.model.User;
import com.chervonnaya.library.model.enums.Role;
import com.chervonnaya.library.repository.UserRepository;
import com.chervonnaya.library.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, UserDTO, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository repository, ModelMapper mapper) {
        super(repository, User.class, mapper);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findUserByEmail(email)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + email));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(repository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(User.class.getSimpleName())));
    }

    @Override
    public Page<User> findUserByRole(Role role, Pageable pageable) {
        return repository.findUserByRole(role, pageable);
    }
}
