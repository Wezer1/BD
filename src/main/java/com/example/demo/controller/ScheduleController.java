package com.example.demo.controller;

import com.example.demo.dto.FlightDTO;
import com.example.demo.dto.ScheduleDTO;
import com.example.demo.entity.Schedule;
import com.example.demo.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/")
    public ResponseEntity<List<ScheduleDTO>> getSchedule(){
        return ResponseEntity.ok(scheduleService.getAllSchedule());
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Integer scheduleId){
        return ResponseEntity.ok(scheduleService.getScheduleById(scheduleId));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDTO> deleteSchedule(@PathVariable Integer scheduleId){
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<ScheduleDTO> addSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO){
        return ResponseEntity.ok(scheduleService.saveSchedule(scheduleDTO));
    }

    @PostMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDTO> changeSchedule(@PathVariable Integer scheduleId,
                                                  @Valid @RequestBody ScheduleDTO scheduleDTO){
        return ResponseEntity.ok(scheduleService.changeSchedule(scheduleId, scheduleDTO));
    }
}
