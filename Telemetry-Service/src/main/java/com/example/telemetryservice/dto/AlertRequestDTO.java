package com.example.telemetryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertRequestDTO {

    private Long satelliteId;

    private String alertType;

    private String alertMessage;

    private String severity;

    private LocalDateTime alertTime;
}