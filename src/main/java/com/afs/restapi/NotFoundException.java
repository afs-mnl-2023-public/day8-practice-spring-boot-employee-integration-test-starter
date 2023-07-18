package com.afs.restapi;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("employee id not found");
    }
}
