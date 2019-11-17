package com.aerarium.exception;

public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException() {
        super("Invalid operation!");
    }

}
