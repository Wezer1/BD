package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private Integer id;

    private LocalDateTime arrivalDatetime;

    private LocalDateTime departureDatetime;

    private Long flightDuration; // Или можно использовать String для хранения интервала

    private Integer flightId;
}
