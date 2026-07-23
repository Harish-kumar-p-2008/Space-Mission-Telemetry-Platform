package com.example.satelliteservice.exception;

public class DuplicateSatelliteException extends RuntimeException {

    public DuplicateSatelliteException(String message) {
        super(message);
    }
}