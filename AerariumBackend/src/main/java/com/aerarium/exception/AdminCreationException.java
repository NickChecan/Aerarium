package com.aerarium.exception;

public class AdminCreationException extends RuntimeException {

    public AdminCreationException(String message) {
        super(message);
    }

    public AdminCreationException() {
        super("System couldn't create an administration user!");
    }

}
