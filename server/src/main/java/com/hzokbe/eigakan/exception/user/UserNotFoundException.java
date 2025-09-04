package com.hzokbe.eigakan.exception.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message); 
    }
}
