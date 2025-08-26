package com.hzokbe.eigakan.exception.movie;

public class InvalidMovieTitleException extends RuntimeException {
    public InvalidMovieTitleException(String message) {
        super(message);
    }
}
