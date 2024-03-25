package com.chervonnaya.library.repository;

import com.chervonnaya.library.model.User;
import com.chervonnaya.library.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository extends IRepository<User>{
    Optional<User> findUserByEmail(String email);
    Page<User> findUserByRole(Role role, Pageable pageable);
}
