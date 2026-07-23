package com.example.telemetryservice.service;

import com.example.telemetryservice.dto.AlertRequestDTO;
import com.example.telemetryservice.dto.TelemetryDTO;
import com.example.telemetryservice.entity.Telemetry;
import com.example.telemetryservice.exception.TelemetryNotFoundException;
import com.example.telemetryservice.mapper.TelemetryMapper;
import com.example.telemetryservice.repository.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelemetryServiceImpl implements TelemetryService {

    private final TelemetryRepository telemetryRepository;

    private final RestTemplate restTemplate;

    private static final String ALERT_SERVICE_URL =
            "http://localhost:8083/alerts";

    @Override
    public TelemetryDTO createTelemetry(TelemetryDTO telemetryDTO) {

        log.info("Creating new telemetry for Satellite ID: {}",
                telemetryDTO.getSatelliteId());

        Telemetry telemetry = TelemetryMapper.toEntity(telemetryDTO);

        Telemetry savedTelemetry = telemetryRepository.save(telemetry);

        log.info("Telemetry saved successfully with ID: {}",
                savedTelemetry.getTelemetryId());

        // Check telemetry values and create alerts if required
        checkForAlerts(savedTelemetry);

        return TelemetryMapper.toDTO(savedTelemetry);
    }

    @Override
    public List<TelemetryDTO> getAllTelemetry() {

        log.info("Fetching all telemetry records");

        return telemetryRepository.findAll()
                .stream()
                .map(TelemetryMapper::toDTO)
                .toList();
    }

    @Override
    public TelemetryDTO getTelemetryById(Long telemetryId) {

        log.info("Fetching telemetry with ID: {}", telemetryId);

        Telemetry telemetry = telemetryRepository.findById(telemetryId)
                .orElseThrow(() -> {
                    log.error("Telemetry not found with ID: {}",
                            telemetryId);

                    return new TelemetryNotFoundException(
                            "Telemetry not found with ID: " + telemetryId
                    );
                });

        return TelemetryMapper.toDTO(telemetry);
    }

    @Override
    public List<TelemetryDTO> getTelemetryBySatelliteId(Long satelliteId) {

        log.info("Fetching telemetry for Satellite ID: {}",
                satelliteId);

        return telemetryRepository.findBySatelliteId(satelliteId)
                .stream()
                .map(TelemetryMapper::toDTO)
                .toList();
    }

    @Override
    public TelemetryDTO updateTelemetry(
            Long telemetryId,
            TelemetryDTO telemetryDTO) {

        log.info("Updating telemetry with ID: {}", telemetryId);

        Telemetry telemetry = telemetryRepository.findById(telemetryId)
                .orElseThrow(() -> {
                    log.error("Telemetry not found with ID: {}",
                            telemetryId);

                    return new TelemetryNotFoundException(
                            "Telemetry not found with ID: " + telemetryId
                    );
                });

        telemetry.setSatelliteId(telemetryDTO.getSatelliteId());
        telemetry.setBatteryLevel(telemetryDTO.getBatteryLevel());
        telemetry.setTemperature(telemetryDTO.getTemperature());
        telemetry.setSignalStrength(telemetryDTO.getSignalStrength());
        telemetry.setAltitude(telemetryDTO.getAltitude());
        telemetry.setTimestamp(telemetryDTO.getTimestamp());

        Telemetry updatedTelemetry =
                telemetryRepository.save(telemetry);

        log.info("Telemetry updated successfully with ID: {}",
                telemetryId);

        // Check updated values for possible alerts
        checkForAlerts(updatedTelemetry);

        return TelemetryMapper.toDTO(updatedTelemetry);
    }

    @Override
    public void deleteTelemetry(Long telemetryId) {

        log.info("Deleting telemetry with ID: {}", telemetryId);

        Telemetry telemetry = telemetryRepository.findById(telemetryId)
                .orElseThrow(() -> {
                    log.error("Telemetry not found with ID: {}",
                            telemetryId);

                    return new TelemetryNotFoundException(
                            "Telemetry not found with ID: " + telemetryId
                    );
                });

        telemetryRepository.delete(telemetry);

        log.info("Telemetry deleted successfully with ID: {}",
                telemetryId);
    }

    /*
     * Checks the telemetry values and sends alerts
     * to the Alert Service when abnormal values are detected.
     */
    private void checkForAlerts(Telemetry telemetry) {

        log.info("Checking telemetry values for alerts. Satellite ID: {}",
                telemetry.getSatelliteId());

        if (telemetry.getBatteryLevel() < 20) {

            log.warn("Low battery detected for Satellite ID: {}",
                    telemetry.getSatelliteId());

            sendAlert(
                    telemetry.getSatelliteId(),
                    "LOW BATTERY",
                    "Battery level dropped below 20%",
                    "HIGH"
            );
        }

        if (telemetry.getTemperature() > 45) {

            log.warn("High temperature detected for Satellite ID: {}",
                    telemetry.getSatelliteId());

            sendAlert(
                    telemetry.getSatelliteId(),
                    "OVERHEAT",
                    "Temperature exceeded 45 degrees Celsius",
                    "HIGH"
            );
        }

        if (telemetry.getSignalStrength() < 50) {

            log.warn("Low signal strength detected for Satellite ID: {}",
                    telemetry.getSatelliteId());

            sendAlert(
                    telemetry.getSatelliteId(),
                    "COMMUNICATION FAILURE",
                    "Signal strength dropped below 50",
                    "HIGH"
            );
        }
    }

    /*
     * Sends the alert to the Alert Service
     * using RestTemplate.
     */
    private void sendAlert(
            Long satelliteId,
            String alertType,
            String alertMessage,
            String severity) {

        AlertRequestDTO alertRequest = new AlertRequestDTO();

        alertRequest.setSatelliteId(satelliteId);
        alertRequest.setAlertType(alertType);
        alertRequest.setAlertMessage(alertMessage);
        alertRequest.setSeverity(severity);
        alertRequest.setAlertTime(LocalDateTime.now());

        log.info(
                "Sending {} alert to Alert Service for Satellite ID: {}",
                alertType,
                satelliteId
        );

        restTemplate.postForObject(
                ALERT_SERVICE_URL,
                alertRequest,
                Object.class
        );

        log.info(
                "Alert sent successfully for Satellite ID: {}",
                satelliteId
        );
    }
}