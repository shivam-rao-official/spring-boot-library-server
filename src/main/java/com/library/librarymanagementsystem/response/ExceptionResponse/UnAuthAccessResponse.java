package com.library.librarymanagementsystem.response.ExceptionResponse;


import com.library.librarymanagementsystem.exception.UnauthorisedAccess;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UnAuthAccessResponse {

    @ExceptionHandler(UnauthorisedAccess.class)
    public ResponseEntity<?> userNotExistsException(UnauthorisedAccess ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
