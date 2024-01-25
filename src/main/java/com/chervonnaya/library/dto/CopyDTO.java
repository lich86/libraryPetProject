package com.chervonnaya.library.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CopyDTO extends BaseDTO{
    private BigDecimal price;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private Long readerId;
}
