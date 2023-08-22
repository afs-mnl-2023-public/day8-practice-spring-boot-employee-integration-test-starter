package com.afs.restapi.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("employee id not found");
    }
}
