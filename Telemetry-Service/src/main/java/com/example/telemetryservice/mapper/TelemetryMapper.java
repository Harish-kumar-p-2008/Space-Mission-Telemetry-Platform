package com.example.telemetryservice.mapper;

import com.example.telemetryservice.dto.TelemetryDTO;
import com.example.telemetryservice.entity.Telemetry;

public class TelemetryMapper {

    public static Telemetry toEntity(TelemetryDTO dto) {

        Telemetry telemetry = new Telemetry();

        telemetry.setTelemetryId(dto.getTelemetryId());
        telemetry.setSatelliteId(dto.getSatelliteId());
        telemetry.setBatteryLevel(dto.getBatteryLevel());
        telemetry.setTemperature(dto.getTemperature());
        telemetry.setSignalStrength(dto.getSignalStrength());
        telemetry.setAltitude(dto.getAltitude());
        telemetry.setTimestamp(dto.getTimestamp());

        return telemetry;
    }

    public static TelemetryDTO toDTO(Telemetry telemetry) {

        TelemetryDTO dto = new TelemetryDTO();

        dto.setTelemetryId(telemetry.getTelemetryId());
        dto.setSatelliteId(telemetry.getSatelliteId());
        dto.setBatteryLevel(telemetry.getBatteryLevel());
        dto.setTemperature(telemetry.getTemperature());
        dto.setSignalStrength(telemetry.getSignalStrength());
        dto.setAltitude(telemetry.getAltitude());
        dto.setTimestamp(telemetry.getTimestamp());

        return dto;
    }
}