package com.example.maintenanceservice.mapper;

import com.example.maintenanceservice.dto.MaintenanceDTO;
import com.example.maintenanceservice.entity.Maintenance;

public class MaintenanceMapper {

    private MaintenanceMapper() {
        // Utility class
    }

    public static Maintenance toEntity(
            MaintenanceDTO maintenanceDTO) {

        Maintenance maintenance = new Maintenance();

        maintenance.setMaintenanceId(
                maintenanceDTO.getMaintenanceId());

        maintenance.setSatelliteId(
                maintenanceDTO.getSatelliteId());

        maintenance.setMaintenanceType(
                maintenanceDTO.getMaintenanceType());

        maintenance.setDescription(
                maintenanceDTO.getDescription());

        maintenance.setScheduledDate(
                maintenanceDTO.getScheduledDate());

        maintenance.setStatus(
                maintenanceDTO.getStatus());

        return maintenance;
    }

    public static MaintenanceDTO toDTO(
            Maintenance maintenance) {

        MaintenanceDTO maintenanceDTO =
                new MaintenanceDTO();

        maintenanceDTO.setMaintenanceId(
                maintenance.getMaintenanceId());

        maintenanceDTO.setSatelliteId(
                maintenance.getSatelliteId());

        maintenanceDTO.setMaintenanceType(
                maintenance.getMaintenanceType());

        maintenanceDTO.setDescription(
                maintenance.getDescription());

        maintenanceDTO.setScheduledDate(
                maintenance.getScheduledDate());

        maintenanceDTO.setStatus(
                maintenance.getStatus());

        return maintenanceDTO;
    }
}