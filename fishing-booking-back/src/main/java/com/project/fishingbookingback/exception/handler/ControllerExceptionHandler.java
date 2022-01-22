package com.project.fishingbookingback.exception.handler;


import com.project.fishingbookingback.exception.*;
import org.springframework.dao.PessimisticLockingFailureException;
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

    @ExceptionHandler(PessimisticLockingFailureException.class)
    public ResponseEntity<ErrorMessage> dataAlreadyLocked(EntityAlreadyExistsException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
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

    @ExceptionHandler(UserNotConfirmedException.class)
    public ResponseEntity<ErrorMessage> UserNotConfirmedException(UserNotConfirmedException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<ErrorMessage> NotAllowedException(NotAllowedException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnrecognizedTypeException.class)
    public ResponseEntity<ErrorMessage> UnrecognizedTypeException(UnrecognizedTypeException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NewAvailablePeriodOverlapsException.class)
    public ResponseEntity<ErrorMessage> NewAvailablePeriodOverlapsException(NewAvailablePeriodOverlapsException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityOccupiedException.class)
    public ResponseEntity<ErrorMessage> UnrecognizedTypeException(EntityOccupiedException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
