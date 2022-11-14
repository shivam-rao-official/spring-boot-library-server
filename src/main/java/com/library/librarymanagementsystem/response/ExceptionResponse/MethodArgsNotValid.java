package com.library.librarymanagementsystem.response.ExceptionResponse;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MethodArgsNotValid {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> userNotExistsException(MethodArgumentNotValidException ex) {
        Map<String, Object> exceptionMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            exceptionMap.put(e.getField(), e.getDefaultMessage());
        });
        exceptionMap.put("success", false);
        exceptionMap.put("status", HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(exceptionMap);
    }
}
