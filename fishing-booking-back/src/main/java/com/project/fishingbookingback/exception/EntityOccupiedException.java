package com.project.fishingbookingback.exception;

public class EntityOccupiedException extends RuntimeException {
    public EntityOccupiedException() {
        super("Entity is occupied!");
    }
}
