package com.example.satelliteservice.mapper;

import com.example.satelliteservice.dto.SatelliteDTO;
import com.example.satelliteservice.entity.Satellite;

public class SatelliteMapper {

    public static Satellite toEntity(SatelliteDTO dto) {

        Satellite satellite = new Satellite();

        satellite.setSatelliteId(dto.getSatelliteId());
        satellite.setName(dto.getName());
        satellite.setMissionName(dto.getMissionName());
        satellite.setLaunchDate(dto.getLaunchDate());
        satellite.setOrbitType(dto.getOrbitType());
        satellite.setStatus(dto.getStatus());

        return satellite;
    }

    public static SatelliteDTO toDTO(Satellite satellite) {

        SatelliteDTO dto = new SatelliteDTO();

        dto.setSatelliteId(satellite.getSatelliteId());
        dto.setName(satellite.getName());
        dto.setMissionName(satellite.getMissionName());
        dto.setLaunchDate(satellite.getLaunchDate());
        dto.setOrbitType(satellite.getOrbitType());
        dto.setStatus(satellite.getStatus());

        return dto;
    }
}