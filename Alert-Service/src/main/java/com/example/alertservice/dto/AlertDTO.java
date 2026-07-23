package com.example.alertservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlertDTO {

    private Long alertId;

    @NotNull(message = "Satellite ID is required")
    private Long satelliteId;

    @NotBlank(message = "Alert type is required")
    private String alertType;

    @NotBlank(message = "Alert message is required")
    private String alertMessage;

    @NotBlank(message = "Severity is required")
    private String severity;

    @NotNull(message = "Alert time is required")
    private LocalDateTime alertTime;
}