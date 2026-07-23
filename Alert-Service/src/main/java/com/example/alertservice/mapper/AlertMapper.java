package com.example.alertservice.mapper;

import com.example.alertservice.dto.AlertDTO;
import com.example.alertservice.entity.Alert;

public class AlertMapper {

    public static Alert toEntity(AlertDTO dto) {

        Alert alert = new Alert();

        alert.setAlertId(dto.getAlertId());
        alert.setSatelliteId(dto.getSatelliteId());
        alert.setAlertType(dto.getAlertType());
        alert.setAlertMessage(dto.getAlertMessage());
        alert.setSeverity(dto.getSeverity());
        alert.setAlertTime(dto.getAlertTime());

        return alert;
    }

    public static AlertDTO toDTO(Alert alert) {

        AlertDTO dto = new AlertDTO();

        dto.setAlertId(alert.getAlertId());
        dto.setSatelliteId(alert.getSatelliteId());
        dto.setAlertType(alert.getAlertType());
        dto.setAlertMessage(alert.getAlertMessage());
        dto.setSeverity(alert.getSeverity());
        dto.setAlertTime(alert.getAlertTime());

        return dto;
    }
}