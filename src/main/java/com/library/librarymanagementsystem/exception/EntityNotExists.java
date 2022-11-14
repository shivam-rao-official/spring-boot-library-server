package com.library.librarymanagementsystem.exception;

public class EntityNotExists extends RuntimeException{
    public EntityNotExists(String message) {
        super(message);
    }
}
