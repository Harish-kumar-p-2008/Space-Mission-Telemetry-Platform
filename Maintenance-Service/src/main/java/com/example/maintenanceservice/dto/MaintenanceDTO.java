package com.example.maintenanceservice.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDTO {

    private Long maintenanceId;

    @NotNull(message = "Satellite ID is required")
    @Positive(message = "Satellite ID must be greater than zero")
    private Long satelliteId;

    @NotBlank(message = "Maintenance type is required")
    private String maintenanceType;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Scheduled date is required")
    @FutureOrPresent(message = "Scheduled date cannot be in the past")
    private LocalDate scheduledDate;

    @NotBlank(message = "Status is required")
    private String status;
}