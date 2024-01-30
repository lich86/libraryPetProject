package com.chervonnaya.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

import static java.time.LocalDateTime.now;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(final EntityNotFoundException e){
        ErrorResponse response = new ErrorResponse(e.getEntityClass() + " not found", HttpStatus.NOT_FOUND, now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DtoValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(final DtoValidationException e) {
        final BindingResult bindingResult = e.getBindingResult();
        ErrorResponse response;
        if (bindingResult.hasErrors()) {
            String message = "Validation failed for field: " +
                    "Field Name: " + bindingResult.getFieldError().getField() +
                    ", Rejected Value: " + bindingResult.getFieldError().getRejectedValue() +
                    ", Default Message: " + bindingResult.getFieldError().getDefaultMessage();
            response = new ErrorResponse(message, HttpStatus.BAD_REQUEST, now());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = new ErrorResponse("Validation failed with unknown error", HttpStatus.BAD_REQUEST, now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleSQLException(SQLException e) {
        ErrorResponse response;
        if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
            response = new ErrorResponse("Duplicate entry detected. Please handle accordingly.", HttpStatus.BAD_REQUEST, now());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response = new ErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, now());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(SaveEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleSaveEntityException(final SaveEntityException e){
        ErrorResponse response = new ErrorResponse("The " + e.getEntityClass() + "wasn't save in database", HttpStatus.UNPROCESSABLE_ENTITY, now());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
