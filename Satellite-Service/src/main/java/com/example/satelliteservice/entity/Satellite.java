package com.example.satelliteservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "satellites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Satellite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long satelliteId;

    @NotBlank(message = "Satellite name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Mission name is required")
    @Column(nullable = false)
    private String missionName;

    @NotNull(message = "Launch date is required")
    @Column(nullable = false)
    private LocalDate launchDate;

    @NotBlank(message = "Orbit type is required")
    @Column(nullable = false)
    private String orbitType;

    @NotBlank(message = "Status is required")
    @Column(nullable = false)
    private String status;
}