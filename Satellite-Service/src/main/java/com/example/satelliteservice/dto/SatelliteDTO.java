package com.example.satelliteservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SatelliteDTO {

    private Long satelliteId;

    @NotBlank(message = "Satellite name is required")
    private String name;

    @NotBlank(message = "Mission name is required")
    private String missionName;

    @NotNull(message = "Launch date is required")
    private LocalDate launchDate;

    @NotBlank(message = "Orbit type is required")
    private String orbitType;

    @NotBlank(message = "Status is required")
    private String status;
}