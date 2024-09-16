package com.Kanso.EmployeeControlApp.exception;
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
