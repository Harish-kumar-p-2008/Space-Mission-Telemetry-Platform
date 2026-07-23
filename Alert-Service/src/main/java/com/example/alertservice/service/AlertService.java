package com.example.alertservice.service;

import com.example.alertservice.dto.AlertDTO;

import java.util.List;

public interface AlertService {

    AlertDTO createAlert(AlertDTO alertDTO);

    List<AlertDTO> getAllAlerts();

    AlertDTO getAlertById(Long alertId);

    List<AlertDTO> getAlertsBySatelliteId(Long satelliteId);

    AlertDTO updateAlert(Long alertId, AlertDTO alertDTO);

    void deleteAlert(Long alertId);
}