package com.example.alertservice.controller;

import com.example.alertservice.dto.AlertDTO;
import com.example.alertservice.service.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @PostMapping
    public ResponseEntity<AlertDTO> createAlert(
            @Valid @RequestBody AlertDTO alertDTO) {

        log.info("POST /alerts - Creating alert for Satellite ID: {}",
                alertDTO.getSatelliteId());

        return new ResponseEntity<>(
                alertService.createAlert(alertDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<AlertDTO>> getAllAlerts() {

        log.info("GET /alerts - Fetching all alerts");

        return ResponseEntity.ok(
                alertService.getAllAlerts()
        );
    }

    @GetMapping("/{alertId}")
    public ResponseEntity<AlertDTO> getAlertById(
            @PathVariable Long alertId) {

        log.info("GET /alerts/{} - Fetching alert", alertId);

        return ResponseEntity.ok(
                alertService.getAlertById(alertId)
        );
    }

    @GetMapping("/satellite/{satelliteId}")
    public ResponseEntity<List<AlertDTO>> getAlertsBySatelliteId(
            @PathVariable Long satelliteId) {

        log.info("GET /alerts/satellite/{} - Fetching alerts for satellite",
                satelliteId);

        return ResponseEntity.ok(
                alertService.getAlertsBySatelliteId(satelliteId)
        );
    }

    @PutMapping("/{alertId}")
    public ResponseEntity<AlertDTO> updateAlert(
            @PathVariable Long alertId,
            @Valid @RequestBody AlertDTO alertDTO) {

        log.info("PUT /alerts/{} - Updating alert", alertId);

        return ResponseEntity.ok(
                alertService.updateAlert(alertId, alertDTO)
        );
    }

    @DeleteMapping("/{alertId}")
    public ResponseEntity<String> deleteAlert(
            @PathVariable Long alertId) {

        log.info("DELETE /alerts/{} - Deleting alert", alertId);

        alertService.deleteAlert(alertId);

        return ResponseEntity.ok(
                "Alert deleted successfully."
        );
    }
}