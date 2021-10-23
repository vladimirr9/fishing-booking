package com.project.fishingbookingback.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid combination of username and password");
    }
}
