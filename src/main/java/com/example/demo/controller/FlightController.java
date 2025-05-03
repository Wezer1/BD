package com.example.demo.controller;


import com.example.demo.dto.FlightDTO;
import com.example.demo.dto.FlightFilterDTO;
import com.example.demo.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping("/")
    public ResponseEntity<List<FlightDTO>> getFlight(){
        return ResponseEntity.ok(flightService.getAllFlight());
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable Integer flightId){
        return ResponseEntity.ok(flightService.getFlightById(flightId));
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<FlightDTO> deleteFlight(@PathVariable Integer flightId){
        flightService.deleteFlight(flightId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<FlightDTO> addFlight(@Valid @RequestBody FlightDTO flightDTO){
        return ResponseEntity.ok(flightService.saveFlight(flightDTO));
    }

    @PostMapping("/{flightId}")
    public ResponseEntity<FlightDTO> changeFlight(@PathVariable Integer flightId,
                                                @Valid @RequestBody FlightDTO flightDTO){
        return ResponseEntity.ok(flightService.changeFlight(flightId, flightDTO));
    }
//TODO: реализовать фильтрацию с помощью EntityFilter

    @GetMapping("/search")
    public ResponseEntity<List<FlightDTO>> searchFlights(
            @RequestParam(required = false) String departure,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) Integer airplaneId,
            @RequestParam(required = false) String flightNumber,
            @RequestParam(defaultValue = "id") String sortColumn,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        // Создаем DTO для фильтрации
        FlightFilterDTO filter = new FlightFilterDTO();
        filter.setDeparture(departure);
        filter.setDestination(destination);
        filter.setAirplaneId(airplaneId);
        filter.setFlightNumber(flightNumber);

        // Вызываем сервис с фильтрацией и сортировкой
        return ResponseEntity.ok(flightService.filterFlights(filter, sortColumn, sortDirection));
    }
}
