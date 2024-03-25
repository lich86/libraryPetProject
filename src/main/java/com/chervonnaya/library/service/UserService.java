package com.chervonnaya.library.service;

import com.chervonnaya.library.dto.UserDTO;
import com.chervonnaya.library.model.User;
import com.chervonnaya.library.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends CrudService<User, UserDTO>, UserDetailsService {

    Optional<User> findUserByEmail(String email);

    Page<User> findUserByRole(Role role, Pageable pageable);
}
