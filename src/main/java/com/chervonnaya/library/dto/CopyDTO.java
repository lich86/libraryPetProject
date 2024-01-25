package com.chervonnaya.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CopyDTO extends BaseDTO{
    @NotBlank(message = "{copyPrice.notBlank}")
    private BigDecimal price;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private Long readerId;
}
