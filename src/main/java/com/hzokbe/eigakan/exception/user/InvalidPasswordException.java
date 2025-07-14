package com.hzokbe.eigakan.exception.user;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message); 
    }
}
