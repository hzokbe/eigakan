package com.hzokbe.eigakan.exception.user;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException(String message) {
        super(message); 
    }
}
