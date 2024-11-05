package org.f5games.aiden_game.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler{
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String>handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String>handleDataIntegrityViolation(DataIntegrityViolationException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
