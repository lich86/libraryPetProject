package com.chervonnaya.library.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final String message;
    private final int status;
    private final String error;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }

}