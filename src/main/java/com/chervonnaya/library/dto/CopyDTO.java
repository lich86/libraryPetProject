package com.chervonnaya.library.dto;

import com.chervonnaya.library.config.constants.ValidationConst;
import com.chervonnaya.library.model.enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@Getter
@Setter
public class CopyDTO extends BaseDTO{
    @NotBlank(message = "{copyTitle.notBlank}")
    @Size(max = ValidationConst.MAX_LENGTH_BOOK_TITLE)
    private String title;
    private Language language;
    @NotNull(message = "{copyPrice.notNull}")
    private Double price;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private String publishingHouse;
    private Year publishingYear;
    private Long bookId;
    private List<Long> readerIds;
}
