package com.chervonnaya.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class DtoValidationException extends RuntimeException {
    final BindingResult bindingResult;
}
