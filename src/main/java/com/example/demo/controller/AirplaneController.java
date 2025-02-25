package com.example.demo.controller;

import com.example.demo.dto.AirplaneDTO;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.service.AirplaneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/airplane")
@RequiredArgsConstructor
public class AirplaneController {
    private final AirplaneService airplaneService;

    @GetMapping("/")
    public ResponseEntity<List<AirplaneDTO>> getAirplanes(){
        return ResponseEntity.ok(airplaneService.getAllAirplanes());
    }

    @GetMapping("/{airplaneId}")
    public ResponseEntity<AirplaneDTO> getAirplaneById(@PathVariable Integer airplaneId){
        return ResponseEntity.ok(airplaneService.getAirplaneById(airplaneId));
    }

    @DeleteMapping("/{airplaneId}")
    public ResponseEntity<AirplaneDTO> deleteAirplane(@PathVariable Integer airplaneId){
        airplaneService.deleteAirplane(airplaneId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<AirplaneDTO> addAirplane(@Valid @RequestBody AirplaneDTO airplaneDTO){
        return ResponseEntity.ok(airplaneService.saveAirplane(airplaneDTO));
    }

    @PostMapping("/{airplaneId}")
    public ResponseEntity<AirplaneDTO> changeAirplane(@PathVariable Integer airplaneId,
                                                @Valid @RequestBody AirplaneDTO airplaneDTO){
        return ResponseEntity.ok(airplaneService.changeAirplane(airplaneId, airplaneDTO));
    }
}
