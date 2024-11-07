package org.f5games.aiden_game.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.f5games.aiden_game.ControllerExceptionHandler;
import org.f5games.aiden_game.exceptions.InvalidDataException;
import org.f5games.aiden_game.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    void testHandleIllegalArgument() {
        IllegalArgumentException exception = new IllegalArgumentException("Test IllegalArgumentException");
        ResponseEntity<String> response = exceptionHandler.handleIllegalArgument(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Test IllegalArgumentException", response.getBody());
    }

    @Test
    void testHandleDataIntegrityViolation() {
        DataIntegrityViolationException exception = new DataIntegrityViolationException(
                "Test DataIntegrityViolationException");
        ResponseEntity<String> response = exceptionHandler.handleDataIntegrityViolation(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Test DataIntegrityViolationException", response.getBody());
    }

    @Test
    void testHandleResourceNotFound() {
        ResourceNotFoundException exception = new ResourceNotFoundException(
                "Test ResourceNotFoundException");
        ResponseEntity<String> response = exceptionHandler.handleResourceNotFound(exception);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Test ResourceNotFoundException", response.getBody());
    }

    @Test
    void testHandleInvalidData() {
        InvalidDataException exception = new InvalidDataException(
                "Test InvalidDataException");
        ResponseEntity<String> response = exceptionHandler.handleInvalidData(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Test InvalidDataException", response.getBody());
    }
}