package com.example.crud.Exception;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
