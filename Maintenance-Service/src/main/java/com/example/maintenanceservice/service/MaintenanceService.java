package com.example.maintenanceservice.service;

import com.example.maintenanceservice.dto.MaintenanceDTO;

import java.util.List;

public interface MaintenanceService {

    MaintenanceDTO createMaintenance(
            MaintenanceDTO maintenanceDTO);

    List<MaintenanceDTO> getAllMaintenance();

    MaintenanceDTO getMaintenanceById(
            Long maintenanceId);

    List<MaintenanceDTO> getMaintenanceBySatelliteId(
            Long satelliteId);

    MaintenanceDTO updateMaintenance(
            Long maintenanceId,
            MaintenanceDTO maintenanceDTO);

    void deleteMaintenance(Long maintenanceId);
}