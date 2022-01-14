package com.project.fishingbookingback.exception;

public class NewAvailablePeriodOverlapsException extends RuntimeException {
    public NewAvailablePeriodOverlapsException(String content, Long id) {
        super("Available period overlaps " + content + " " + id.toString());
    }
}
