package com.example.maintenanceservice.repository;

import com.example.maintenanceservice.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository
        extends JpaRepository<Maintenance, Long> {

    List<Maintenance> findBySatelliteId(Long satelliteId);
}