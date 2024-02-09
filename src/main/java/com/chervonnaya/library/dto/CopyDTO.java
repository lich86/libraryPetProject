package com.chervonnaya.library.dto;

import com.chervonnaya.library.config.constants.ValidationConst;
import com.chervonnaya.library.model.enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Year;


@Getter
@Setter
public class CopyDTO extends BaseDTO{
    @NotBlank(message = "{copyTitle.notBlank}")
    @Size(max = ValidationConst.MAX_LENGTH_BOOK_TITLE)
    private String title;
    private Language language;
    @NotNull(message = "{copyPrice.notNull}")
    private Double price;
    private String publishingHouse;
    @PastOrPresent
    private Year publishingYear;
    private String translator;
    private Long bookId;
}
