package com.example.maintenanceservice.controller;

import com.example.maintenanceservice.dto.MaintenanceDTO;
import com.example.maintenanceservice.service.MaintenanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<MaintenanceDTO> createMaintenance(
            @Valid @RequestBody MaintenanceDTO maintenanceDTO) {

        log.info("POST /maintenance - Creating maintenance for Satellite ID: {}",
                maintenanceDTO.getSatelliteId());

        MaintenanceDTO savedMaintenance =
                maintenanceService.createMaintenance(maintenanceDTO);

        return new ResponseEntity<>(
                savedMaintenance,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceDTO>>
    getAllMaintenance() {

        log.info("GET /maintenance - Fetching all maintenance records");

        return ResponseEntity.ok(
                maintenanceService.getAllMaintenance()
        );
    }

    @GetMapping("/{maintenanceId}")
    public ResponseEntity<MaintenanceDTO> getMaintenanceById(
            @PathVariable Long maintenanceId) {

        log.info("GET /maintenance/{} - Fetching maintenance record",
                maintenanceId);

        return ResponseEntity.ok(
                maintenanceService.getMaintenanceById(
                        maintenanceId
                )
        );
    }

    @GetMapping("/satellite/{satelliteId}")
    public ResponseEntity<List<MaintenanceDTO>>
    getMaintenanceBySatelliteId(
            @PathVariable Long satelliteId) {

        log.info("GET /maintenance/satellite/{} - Fetching maintenance records",
                satelliteId);

        return ResponseEntity.ok(
                maintenanceService
                        .getMaintenanceBySatelliteId(satelliteId)
        );
    }

    @PutMapping("/{maintenanceId}")
    public ResponseEntity<MaintenanceDTO> updateMaintenance(
            @PathVariable Long maintenanceId,
            @Valid @RequestBody MaintenanceDTO maintenanceDTO) {

        log.info("PUT /maintenance/{} - Updating maintenance record",
                maintenanceId);

        return ResponseEntity.ok(
                maintenanceService.updateMaintenance(
                        maintenanceId,
                        maintenanceDTO
                )
        );
    }

    @DeleteMapping("/{maintenanceId}")
    public ResponseEntity<Void> deleteMaintenance(
            @PathVariable Long maintenanceId) {

        log.info("DELETE /maintenance/{} - Deleting maintenance record",
                maintenanceId);

        maintenanceService.deleteMaintenance(maintenanceId);

        return ResponseEntity.noContent().build();
    }
}