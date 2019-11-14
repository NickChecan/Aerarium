package com.aerarium.exception;

public class MissingPasswordException extends RuntimeException {

    public MissingPasswordException(String message) {
        super(message);
    }

    public MissingPasswordException() {
        super("Missing Password.");
    }

}
