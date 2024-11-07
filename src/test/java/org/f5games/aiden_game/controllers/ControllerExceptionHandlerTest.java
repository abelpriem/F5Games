package org.f5games.aiden_game.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.f5games.aiden_game.ControllerExceptionHandler;
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
        // Preparar la excepción
        IllegalArgumentException exception = new IllegalArgumentException("Test IllegalArgumentException");

        // Invocar el método de manejo de excepción
        ResponseEntity<String> response = exceptionHandler.handleIllegalArgument(exception);

        // Verificar que el estado de la respuesta es 400 Bad Request
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verificar que el cuerpo de la respuesta contiene el mensaje de la excepción
        assertEquals("Test IllegalArgumentException", response.getBody());
    }

    @Test
    void testHandleDataIntegrityViolation() {
        // Preparar la excepción
        DataIntegrityViolationException exception = new DataIntegrityViolationException(
                "Test DataIntegrityViolationException");

        // Invocar el método de manejo de excepción
        ResponseEntity<String> response = exceptionHandler.handleDataIntegrityViolation(exception);

        // Verificar que el estado de la respuesta es 400 Bad Request
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verificar que el cuerpo de la respuesta contiene el mensaje de la excepción
        assertEquals("Test DataIntegrityViolationException", response.getBody());
    }
}