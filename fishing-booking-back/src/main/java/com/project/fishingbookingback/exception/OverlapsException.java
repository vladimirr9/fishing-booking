package com.project.fishingbookingback.exception;

public class OverlapsException extends RuntimeException {
    public OverlapsException(String content, Long id) {
        super("This overlaps " + content + " " + id.toString());
    }
}
