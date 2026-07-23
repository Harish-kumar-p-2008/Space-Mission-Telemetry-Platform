package com.example.telemetryservice.service;

import com.example.telemetryservice.dto.TelemetryDTO;

import java.util.List;

public interface TelemetryService {

    TelemetryDTO createTelemetry(TelemetryDTO telemetryDTO);

    List<TelemetryDTO> getAllTelemetry();

    TelemetryDTO getTelemetryById(Long telemetryId);

    List<TelemetryDTO> getTelemetryBySatelliteId(Long satelliteId);

    TelemetryDTO updateTelemetry(Long telemetryId, TelemetryDTO telemetryDTO);

    void deleteTelemetry(Long telemetryId);
}