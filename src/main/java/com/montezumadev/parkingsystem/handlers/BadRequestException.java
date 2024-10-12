package com.montezumadev.parkingsystem.handlers;


public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}