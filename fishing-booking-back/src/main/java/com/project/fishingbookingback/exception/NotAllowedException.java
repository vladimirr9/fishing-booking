package com.project.fishingbookingback.exception;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException() {
        super("User is not allowed to do this.");
    }
}
