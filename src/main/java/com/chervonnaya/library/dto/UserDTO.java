package com.chervonnaya.library.dto;

import com.chervonnaya.library.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {

    @NotBlank(message = "{email.notBlank}")
    private String email;
    @NotBlank(message = "{password.notBlank}")
    private String password;
    @NotNull
    private Role role;
}
