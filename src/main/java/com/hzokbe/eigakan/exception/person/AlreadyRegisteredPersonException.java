package com.hzokbe.eigakan.exception.person;

public class AlreadyRegisteredPersonException extends RuntimeException {
    public AlreadyRegisteredPersonException(String message) {
        super(message);
    }
}
