package com.example.satelliteservice.service;

import com.example.satelliteservice.dto.SatelliteDTO;
import com.example.satelliteservice.entity.Satellite;
import com.example.satelliteservice.exception.DuplicateSatelliteException;
import com.example.satelliteservice.exception.SatelliteNotFoundException;
import com.example.satelliteservice.mapper.SatelliteMapper;
import com.example.satelliteservice.repository.SatelliteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SatelliteServiceImpl implements SatelliteService {

    private final SatelliteRepository satelliteRepository;

    @Override
    public SatelliteDTO createSatellite(SatelliteDTO satelliteDTO) {

        if (satelliteRepository.existsByName(satelliteDTO.getName())) {
            throw new DuplicateSatelliteException("Satellite already exists with name : " + satelliteDTO.getName());
        }

        Satellite satellite = SatelliteMapper.toEntity(satelliteDTO);

        Satellite savedSatellite = satelliteRepository.save(satellite);

        log.info("Satellite created successfully with ID : {}", savedSatellite.getSatelliteId());

        return SatelliteMapper.toDTO(savedSatellite);
    }

    @Override
    public List<SatelliteDTO> getAllSatellites() {

        log.info("Fetching all satellites");

        return satelliteRepository.findAll()
                .stream()
                .map(SatelliteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SatelliteDTO getSatelliteById(Long satelliteId) {

        log.info("Fetching satellite with ID : {}", satelliteId);

        Satellite satellite = satelliteRepository.findById(satelliteId)
                .orElseThrow(() ->
                        new SatelliteNotFoundException("Satellite not found with ID : " + satelliteId));

        return SatelliteMapper.toDTO(satellite);
    }

    @Override
    public SatelliteDTO updateSatellite(Long satelliteId, SatelliteDTO satelliteDTO) {

        log.info("Updating satellite with ID : {}", satelliteId);

        Satellite satellite = satelliteRepository.findById(satelliteId)
                .orElseThrow(() ->
                        new SatelliteNotFoundException("Satellite not found with ID : " + satelliteId));

        satellite.setName(satelliteDTO.getName());
        satellite.setMissionName(satelliteDTO.getMissionName());
        satellite.setLaunchDate(satelliteDTO.getLaunchDate());
        satellite.setOrbitType(satelliteDTO.getOrbitType());
        satellite.setStatus(satelliteDTO.getStatus());

        Satellite updatedSatellite = satelliteRepository.save(satellite);

        return SatelliteMapper.toDTO(updatedSatellite);
    }

    @Override
    public void deleteSatellite(Long satelliteId) {

        log.info("Deleting satellite with ID : {}", satelliteId);

        Satellite satellite = satelliteRepository.findById(satelliteId)
                .orElseThrow(() ->
                        new SatelliteNotFoundException("Satellite not found with ID : " + satelliteId));

        satelliteRepository.delete(satellite);

        log.info("Satellite deleted successfully");
    }
}