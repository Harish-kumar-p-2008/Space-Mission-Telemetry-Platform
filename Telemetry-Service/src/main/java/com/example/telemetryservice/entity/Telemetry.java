package com.example.telemetryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "telemetry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Telemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long telemetryId;

    @NotNull(message = "Satellite ID is required")
    private Long satelliteId;

    @Min(value = 0, message = "Battery level cannot be less than 0")
    @Max(value = 100, message = "Battery level cannot be greater than 100")
    private Integer batteryLevel;

    @NotNull(message = "Temperature is required")
    private Double temperature;

    @Positive(message = "Signal strength must be positive")
    private Double signalStrength;

    @Positive(message = "Altitude must be positive")
    private Double altitude;

    @NotNull(message = "Timestamp is required")
    private LocalDateTime timestamp;
}