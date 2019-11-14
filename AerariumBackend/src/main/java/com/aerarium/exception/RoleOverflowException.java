package com.aerarium.exception;

public class RoleOverflowException extends RuntimeException {

    public RoleOverflowException(String message) {
        super(message);
    }

    public RoleOverflowException() {
        super("Too many roles for the informed user.");
    }

}
