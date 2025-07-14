package com.hzokbe.eigakan.exception.user;

public class AlreadyRegisteredUserException extends RuntimeException {
    public AlreadyRegisteredUserException(String message) {
        super(message);
    }
}
