package com.library.librarymanagementsystem.response.ExceptionResponse;


import com.library.librarymanagementsystem.exception.EntityNotExists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityNotExistException {

    @ExceptionHandler(EntityNotExists.class)
    public ResponseEntity<?> entityNotExistsException(EntityNotExists ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
