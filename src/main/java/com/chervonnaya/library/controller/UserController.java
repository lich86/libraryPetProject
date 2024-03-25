package com.chervonnaya.library.controller;


import com.chervonnaya.library.dto.UserDTO;
import com.chervonnaya.library.model.User;
import com.chervonnaya.library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<User> getUsers(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable(name = "id") Long id) {
        return userService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable(name = "id") Long id,
                           @Valid @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }

}
