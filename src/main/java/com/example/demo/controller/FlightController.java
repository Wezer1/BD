package com.example.demo.controller;


import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.FlightDTO;
import com.example.demo.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
