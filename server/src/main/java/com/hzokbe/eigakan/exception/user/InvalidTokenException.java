package com.hzokbe.eigakan.exception.user;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message); 
    }
}
