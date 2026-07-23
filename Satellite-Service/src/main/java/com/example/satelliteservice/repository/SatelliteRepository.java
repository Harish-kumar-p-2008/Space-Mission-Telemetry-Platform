package com.example.satelliteservice.repository;

import com.example.satelliteservice.entity.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, Long> {

    boolean existsByName(String name);

}