package com.hzokbe.eigakan.exception.movie;

public class AlreadyRegisteredMovieException extends RuntimeException {
    public AlreadyRegisteredMovieException(String message) {
        super(message);
    }
}
