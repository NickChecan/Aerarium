package com.aerarium.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolation(RuntimeException exception, WebRequest request) {
        String message = exception.getCause().getCause().getMessage();
        return handleExceptionInternal(exception, message,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {JpaObjectRetrievalFailureException.class})
    public ResponseEntity<Object> handleJpaObjectRetrievalFailure(RuntimeException exception, WebRequest request) {
        String message = exception.getCause().getMessage();
        return handleExceptionInternal(exception, message,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFound(RuntimeException exception, WebRequest request) {
        String message = exception.getMessage();
        return handleExceptionInternal(exception, message,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccess(RuntimeException exception, WebRequest request) {
        String message = exception.getMessage();
        return handleExceptionInternal(exception, message,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {InvalidDataAccessApiUsageException.class})
    public ResponseEntity<Object> handleInvalidDataAccessApiUsage(RuntimeException exception, WebRequest request) {
        String message = exception.getCause().getCause().getMessage();
        return handleExceptionInternal(exception, message,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {MissingPasswordException.class})
    public ResponseEntity<Object> handleMissingPassword(RuntimeException exception, WebRequest request) {
        String message = exception.getMessage();
        return handleExceptionInternal(exception, message,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {RoleOverflowException.class})
    public ResponseEntity<Object> handleRoleOverflow(RuntimeException exception, WebRequest request) {
        String message = exception.getMessage();
        return handleExceptionInternal(exception, message,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
