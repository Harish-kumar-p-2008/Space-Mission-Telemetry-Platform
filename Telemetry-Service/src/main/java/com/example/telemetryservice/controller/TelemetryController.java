package com.example.telemetryservice.controller;

import com.example.telemetryservice.dto.TelemetryDTO;
import com.example.telemetryservice.service.TelemetryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/telemetry")
@RequiredArgsConstructor
public class TelemetryController {

    private final TelemetryService telemetryService;

    @PostMapping
    public ResponseEntity<TelemetryDTO> createTelemetry(@Valid @RequestBody TelemetryDTO telemetryDTO) {

        log.info("POST /telemetry - Creating telemetry for satellite ID: {}",
                telemetryDTO.getSatelliteId());

        return new ResponseEntity<>(telemetryService.createTelemetry(telemetryDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TelemetryDTO>> getAllTelemetry() {

        log.info("GET /telemetry - Fetching all telemetry data");

        return ResponseEntity.ok(telemetryService.getAllTelemetry());
    }

    @GetMapping("/{telemetryId}")
    public ResponseEntity<TelemetryDTO> getTelemetryById(@PathVariable Long telemetryId) {

        log.info("GET /telemetry/{} - Fetching telemetry", telemetryId);

        return ResponseEntity.ok(telemetryService.getTelemetryById(telemetryId));
    }

    @GetMapping("/satellite/{satelliteId}")
    public ResponseEntity<List<TelemetryDTO>> getTelemetryBySatelliteId(@PathVariable Long satelliteId) {

        log.info("GET /telemetry/satellite/{} - Fetching telemetry for satellite",
                satelliteId);

        return ResponseEntity.ok(telemetryService.getTelemetryBySatelliteId(satelliteId));
    }

    @PutMapping("/{telemetryId}")
    public ResponseEntity<TelemetryDTO> updateTelemetry(
            @PathVariable Long telemetryId,
            @Valid @RequestBody TelemetryDTO telemetryDTO) {

        log.info("PUT /telemetry/{} - Updating telemetry", telemetryId);

        return ResponseEntity.ok(
                telemetryService.updateTelemetry(telemetryId, telemetryDTO));
    }

    @DeleteMapping("/{telemetryId}")
    public ResponseEntity<String> deleteTelemetry(@PathVariable Long telemetryId) {

        log.info("DELETE /telemetry/{} - Deleting telemetry", telemetryId);

        telemetryService.deleteTelemetry(telemetryId);

        return ResponseEntity.ok("Telemetry deleted successfully.");
    }
}