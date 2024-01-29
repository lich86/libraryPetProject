package com.chervonnaya.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleSQLException(SQLException e) {
        if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
            ErrorResponse errorResponse = new ErrorResponse("Duplicate entry detected. Please handle accordingly.", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } else {
            // Handle other SQLExceptions if needed
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
