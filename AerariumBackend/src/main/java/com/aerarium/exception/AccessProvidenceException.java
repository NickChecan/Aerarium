package com.aerarium.exception;

public class AccessProvidenceException extends RuntimeException {

    public AccessProvidenceException(String message) {
        super(message);
    }

    public AccessProvidenceException() {
        super("The system couldn't provide an administration access");
    }

}
