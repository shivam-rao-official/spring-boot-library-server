package com.library.librarymanagementsystem.exception;

public class UnauthorisedAccess extends RuntimeException{
    public UnauthorisedAccess(String message) {
        super(message);
    }
}
