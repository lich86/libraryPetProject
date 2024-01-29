package com.chervonnaya.library.dto;

import com.chervonnaya.library.config.constants.ValidationConst;
import com.chervonnaya.library.model.Genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class BookDTO extends BaseDTO{
    @NotBlank(message = "{bookTitle.notBlank}")
    @Size(max = ValidationConst.MAX_LENGTH_BOOK_TITLE)
    private String title;
    @Size(max = ValidationConst.MAX_LENGTH_BOOK_DESCRIPTION)
    private String description;
    @NotEmpty(message = "{authors.notBlank}")
    private List<Long> authorIds;
    private List<Genre> genres;
}
