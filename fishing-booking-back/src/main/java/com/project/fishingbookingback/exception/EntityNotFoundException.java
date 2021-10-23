package com.project.fishingbookingback.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String className) {
        super(String.format("No %s with this ID found.", className));
    }
}
