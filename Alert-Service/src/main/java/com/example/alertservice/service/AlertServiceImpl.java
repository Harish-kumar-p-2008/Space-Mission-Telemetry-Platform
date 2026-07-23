package com.example.alertservice.service;

import com.example.alertservice.dto.AlertDTO;
import com.example.alertservice.entity.Alert;
import com.example.alertservice.exception.AlertNotFoundException;
import com.example.alertservice.mapper.AlertMapper;
import com.example.alertservice.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;

    @Override
    public AlertDTO createAlert(AlertDTO alertDTO) {

        Alert alert = AlertMapper.toEntity(alertDTO);

        Alert savedAlert = alertRepository.save(alert);

        log.info("Alert created successfully with ID: {}",
                savedAlert.getAlertId());

        return AlertMapper.toDTO(savedAlert);
    }

    @Override
    public List<AlertDTO> getAllAlerts() {

        log.info("Fetching all alerts");

        return alertRepository.findAll()
                .stream()
                .map(AlertMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlertDTO getAlertById(Long alertId) {

        log.info("Fetching alert with ID: {}", alertId);

        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() ->
                        new AlertNotFoundException(
                                "Alert not found with ID: " + alertId
                        ));

        return AlertMapper.toDTO(alert);
    }

    @Override
    public List<AlertDTO> getAlertsBySatelliteId(Long satelliteId) {

        log.info("Fetching alerts for satellite ID: {}", satelliteId);

        return alertRepository.findBySatelliteId(satelliteId)
                .stream()
                .map(AlertMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlertDTO updateAlert(Long alertId, AlertDTO alertDTO) {

        log.info("Updating alert with ID: {}", alertId);

        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() ->
                        new AlertNotFoundException(
                                "Alert not found with ID: " + alertId
                        ));

        alert.setSatelliteId(alertDTO.getSatelliteId());
        alert.setAlertType(alertDTO.getAlertType());
        alert.setAlertMessage(alertDTO.getAlertMessage());
        alert.setSeverity(alertDTO.getSeverity());
        alert.setAlertTime(alertDTO.getAlertTime());

        Alert updatedAlert = alertRepository.save(alert);

        log.info("Alert updated successfully with ID: {}", alertId);

        return AlertMapper.toDTO(updatedAlert);
    }

    @Override
    public void deleteAlert(Long alertId) {

        log.info("Deleting alert with ID: {}", alertId);

        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() ->
                        new AlertNotFoundException(
                                "Alert not found with ID: " + alertId
                        ));

        alertRepository.delete(alert);

        log.info("Alert deleted successfully with ID: {}", alertId);
    }
}