package com.chervonnaya.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class RentalDTO extends BaseDTO{
    @NotNull(message = "{readerId.notBlank}")
    private Long readerId;
    @NotNull(message = "{copyId.notBlank}")
    private Set<Long> copyIds;
    private LocalDateTime returnDate;
}
