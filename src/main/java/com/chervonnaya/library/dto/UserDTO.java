package com.chervonnaya.library.dto;

import com.chervonnaya.library.config.constants.ValidationConst;
import com.chervonnaya.library.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = ValidationConst.EMAIL_PATTERN, message = "{email.pattern}")
    private String email;
    @NotBlank(message = "{password.notBlank}")
    @Pattern(regexp = ValidationConst.PASSWORD_PATTERN, message = "{password.pattern}")
    private String password;
    @NotNull
    private Role role;
}
