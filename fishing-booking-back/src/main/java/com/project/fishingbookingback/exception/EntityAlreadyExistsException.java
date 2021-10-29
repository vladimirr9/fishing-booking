package com.project.fishingbookingback.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String name) {
        super(String.format("Entity %s already exists.", name));
    }
}
