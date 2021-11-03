package com.project.fishingbookingback.exception.handler;


import com.project.fishingbookingback.exception.BadRoleException;
import com.project.fishingbookingback.exception.EntityAlreadyExistsException;
import com.project.fishingbookingback.exception.EntityNotFoundException;
import com.project.fishingbookingback.exception.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> entityAlreadyExistsException(EntityAlreadyExistsException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRoleException.class)
    public ResponseEntity<ErrorMessage> badRoleException(BadRoleException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorMessage> invalidCredentialsException(InvalidCredentialsException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

}
