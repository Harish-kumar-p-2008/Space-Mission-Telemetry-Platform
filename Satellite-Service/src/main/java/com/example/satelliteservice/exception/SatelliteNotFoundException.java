package com.example.satelliteservice.exception;

public class SatelliteNotFoundException extends RuntimeException {

    public SatelliteNotFoundException(String message) {
        super(message);
    }
}