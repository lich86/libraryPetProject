package com.chervonnaya.library.dto;

import com.chervonnaya.library.config.constants.ValidationConst;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Getter
@Setter
public class ReaderDTO extends BaseDTO{
    @NotBlank(message = "{firstName.notBlank}")
    @Size(max = ValidationConst.MAX_LENGTH_NAME, message = "{firstName.size}")
    private String firstName;
    @NotBlank(message = "{lastName.notBlank}")
    @Size(max = ValidationConst.MAX_LENGTH_NAME, message = "{lastName.size}")
    private String lastName;
    private String middleName;
    private List<Long> copyIds;
}
