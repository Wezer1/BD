package com.example.demo.service;

import com.example.demo.dto.FlightDTO;
import com.example.demo.entity.Flight;
import com.example.demo.exceptions.NoSuchException;
import com.example.demo.mapper.FlightMapper;
import com.example.demo.repository.AirplaneRepository;
import com.example.demo.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AirplaneRepository airplaneRepository;

    @Transactional
    public List<FlightDTO> getAllFlight() {
        log.info("Get all flight");
        if (flightRepository.findAll().isEmpty()) {
            throw new NoSuchException("No flight");
        }
        return flightRepository.findAll().stream().map(flightMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public FlightDTO getFlightById(Integer flightId) {
        log.info("Get flight by id: {} ", flightId);
        Optional<Flight> flightOptional = Optional.ofNullable(flightRepository.findById(flightId)
                .orElseThrow(() -> new NoSuchException("There is no flight with ID = " + flightId + " in DB")));
        return flightMapper.toDto(flightOptional.get());
    }

    @Transactional
    public FlightDTO saveFlight(FlightDTO flightDTO) {
        log.info("Saving flight: {}", flightDTO);
        if (airplaneRepository.findById(flightDTO.getAirplaneId()).isEmpty()) {
            throw new NoSuchException("There is no airplane with ID = " + flightDTO.getAirplaneId() + " in Database");
        } else {
            Flight savedFlight = flightRepository.save(flightMapper.toEntity(flightDTO));
            return flightMapper.toDto(savedFlight);
        }
    }

    @Transactional
    public FlightDTO changeFlight(Integer flightId, FlightDTO flightDTO) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if (optionalFlight.isEmpty()) {
            throw new NoSuchException("There is no flight with ID = " + flightId + " in Database");
        } else if (airplaneRepository.findById(flightDTO.getAirplaneId()).isEmpty()) {
            throw new NoSuchException("There is no airplane with ID = " + flightDTO.getAirplaneId() + " in Database");
        } else {
            Flight existingFlight = optionalFlight.get();
            Flight updatedFlight = flightMapper.toEntity(flightDTO);
            existingFlight.setDeparture(updatedFlight.getDeparture());
            existingFlight.setDestination(updatedFlight.getDestination());
            existingFlight.setFlightNumber(updatedFlight.getFlightNumber());
            existingFlight.setAirplaneId(updatedFlight.getAirplaneId());

            return flightMapper.toDto(flightRepository.save(existingFlight));
        }
    }

    @Transactional
    public void deleteFlight(Integer flightId) {
        log.info("Delete flight");
        if (flightRepository.findById(flightId).isEmpty()) {
            throw new NoSuchException("There is no flight with ID = " + flightId + " in Database");
        }
        flightRepository.deleteById(flightId);
    }

}
