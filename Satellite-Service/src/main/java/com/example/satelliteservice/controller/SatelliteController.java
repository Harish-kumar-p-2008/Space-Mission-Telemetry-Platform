package com.example.satelliteservice.controller;

import com.example.satelliteservice.dto.SatelliteDTO;
import com.example.satelliteservice.service.SatelliteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/satellites")
@RequiredArgsConstructor
public class SatelliteController {

    private final SatelliteService satelliteService;

    @PostMapping
    public ResponseEntity<SatelliteDTO> createSatellite(@Valid @RequestBody SatelliteDTO satelliteDTO) {

        log.info("POST /satellites - Creating satellite: {}", satelliteDTO.getName());

        return new ResponseEntity<>(satelliteService.createSatellite(satelliteDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SatelliteDTO>> getAllSatellites() {

        log.info("GET /satellites - Fetching all satellites");

        return ResponseEntity.ok(satelliteService.getAllSatellites());
    }

    @GetMapping("/{satelliteId}")
    public ResponseEntity<SatelliteDTO> getSatelliteById(@PathVariable Long satelliteId) {

        log.info("GET /satellites/{} - Fetching satellite", satelliteId);

        return ResponseEntity.ok(satelliteService.getSatelliteById(satelliteId));
    }

    @PutMapping("/{satelliteId}")
    public ResponseEntity<SatelliteDTO> updateSatellite(@PathVariable Long satelliteId,
                                                        @Valid @RequestBody SatelliteDTO satelliteDTO) {

        log.info("PUT /satellites/{} - Updating satellite", satelliteId);

        return ResponseEntity.ok(satelliteService.updateSatellite(satelliteId, satelliteDTO));
    }

    @DeleteMapping("/{satelliteId}")
    public ResponseEntity<String> deleteSatellite(@PathVariable Long satelliteId) {

        log.info("DELETE /satellites/{} - Deleting satellite", satelliteId);

        satelliteService.deleteSatellite(satelliteId);

        return ResponseEntity.ok("Satellite deleted successfully.");
    }
}