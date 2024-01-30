package com.chervonnaya.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SaveEntityException extends RuntimeException{
    private String entityClass;

}
