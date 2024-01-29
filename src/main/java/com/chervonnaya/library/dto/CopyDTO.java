package com.chervonnaya.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CopyDTO extends BaseDTO{
    @NotNull(message = "{copyPrice.notNull}")
    private Double price;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private Long bookId;
    private List<Long> readerIds;
}
