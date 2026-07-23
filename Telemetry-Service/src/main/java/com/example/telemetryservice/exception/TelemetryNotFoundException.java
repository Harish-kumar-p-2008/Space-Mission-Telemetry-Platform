package com.example.telemetryservice.exception;

public class TelemetryNotFoundException extends RuntimeException {

    public TelemetryNotFoundException(String message) {
        super(message);
    }
}