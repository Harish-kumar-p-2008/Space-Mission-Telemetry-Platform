package com.example.satelliteservice.service;

import com.example.satelliteservice.dto.SatelliteDTO;

import java.util.List;

public interface SatelliteService {

    SatelliteDTO createSatellite(SatelliteDTO satelliteDTO);

    List<SatelliteDTO> getAllSatellites();

    SatelliteDTO getSatelliteById(Long satelliteId);

    SatelliteDTO updateSatellite(Long satelliteId, SatelliteDTO satelliteDTO);

    void deleteSatellite(Long satelliteId);
}