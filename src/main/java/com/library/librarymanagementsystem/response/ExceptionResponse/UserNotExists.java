package com.library.librarymanagementsystem.response.ExceptionResponse;


import com.library.librarymanagementsystem.exception.UserNotExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserNotExists {

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<?> userNotExistsException(UserNotExistsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
