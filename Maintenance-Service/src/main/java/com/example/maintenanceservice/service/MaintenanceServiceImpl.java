package com.example.maintenanceservice.service;

import com.example.maintenanceservice.dto.MaintenanceDTO;
import com.example.maintenanceservice.entity.Maintenance;
import com.example.maintenanceservice.exception.MaintenanceNotFoundException;
import com.example.maintenanceservice.mapper.MaintenanceMapper;
import com.example.maintenanceservice.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaintenanceServiceImpl
        implements MaintenanceService {

    private final MaintenanceRepository
            maintenanceRepository;

    @Override
    public MaintenanceDTO createMaintenance(
            MaintenanceDTO maintenanceDTO) {

        log.info(
                "Creating maintenance record for Satellite ID: {}",
                maintenanceDTO.getSatelliteId()
        );

        Maintenance maintenance =
                MaintenanceMapper.toEntity(maintenanceDTO);

        Maintenance savedMaintenance =
                maintenanceRepository.save(maintenance);

        log.info(
                "Maintenance record created successfully with ID: {}",
                savedMaintenance.getMaintenanceId()
        );

        return MaintenanceMapper.toDTO(savedMaintenance);
    }

    @Override
    public List<MaintenanceDTO> getAllMaintenance() {

        log.info("Fetching all maintenance records");

        return maintenanceRepository.findAll()
                .stream()
                .map(MaintenanceMapper::toDTO)
                .toList();
    }

    @Override
    public MaintenanceDTO getMaintenanceById(
            Long maintenanceId) {

        log.info(
                "Fetching maintenance record with ID: {}",
                maintenanceId
        );

        Maintenance maintenance =
                maintenanceRepository.findById(maintenanceId)
                        .orElseThrow(() -> {

                            log.error(
                                    "Maintenance record not found with ID: {}",
                                    maintenanceId
                            );

                            return new MaintenanceNotFoundException(
                                    "Maintenance record not found with ID: "
                                            + maintenanceId
                            );
                        });

        return MaintenanceMapper.toDTO(maintenance);
    }

    @Override
    public List<MaintenanceDTO>
    getMaintenanceBySatelliteId(Long satelliteId) {

        log.info(
                "Fetching maintenance records for Satellite ID: {}",
                satelliteId
        );

        return maintenanceRepository
                .findBySatelliteId(satelliteId)
                .stream()
                .map(MaintenanceMapper::toDTO)
                .toList();
    }

    @Override
    public MaintenanceDTO updateMaintenance(
            Long maintenanceId,
            MaintenanceDTO maintenanceDTO) {

        log.info(
                "Updating maintenance record with ID: {}",
                maintenanceId
        );

        Maintenance maintenance =
                maintenanceRepository.findById(maintenanceId)
                        .orElseThrow(() -> {

                            log.error(
                                    "Maintenance record not found with ID: {}",
                                    maintenanceId
                            );

                            return new MaintenanceNotFoundException(
                                    "Maintenance record not found with ID: "
                                            + maintenanceId
                            );
                        });

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

        Maintenance updatedMaintenance =
                maintenanceRepository.save(maintenance);

        log.info(
                "Maintenance record updated successfully with ID: {}",
                maintenanceId
        );

        return MaintenanceMapper.toDTO(updatedMaintenance);
    }

    @Override
    public void deleteMaintenance(Long maintenanceId) {

        log.info(
                "Deleting maintenance record with ID: {}",
                maintenanceId
        );

        Maintenance maintenance =
                maintenanceRepository.findById(maintenanceId)
                        .orElseThrow(() -> {

                            log.error(
                                    "Maintenance record not found with ID: {}",
                                    maintenanceId
                            );

                            return new MaintenanceNotFoundException(
                                    "Maintenance record not found with ID: "
                                            + maintenanceId
                            );
                        });

        maintenanceRepository.delete(maintenance);

        log.info(
                "Maintenance record deleted successfully with ID: {}",
                maintenanceId
        );
    }
}