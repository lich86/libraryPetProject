package com.chervonnaya.library.dto;

import com.chervonnaya.library.config.constants.ValidationConst;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class AuthorDTO extends BaseDTO{
    @NotBlank(message = "{firstName.notBlank}")
    @Size(max = ValidationConst.MAX_LENGTH_NAME, message = "{firstName.size}")
    private String firstName;
    @NotBlank(message = "{lastName.notBlank}")
    @Size(max = ValidationConst.MAX_LENGTH_NAME, message = "{lastName.size}")
    private String lastName;
    @Size(max = ValidationConst.MAX_LENGTH_NAME, message = "{middleName.size}")
    private String middleName;
    @Size(max = ValidationConst.MAX_LENGTH_PEN_NAME, message = "{penName.size}")
    private String penName;
    @Size(max = ValidationConst.MAX_LENGTH_BOOK_TITLE)
    private List<String> bookTitles;

}
