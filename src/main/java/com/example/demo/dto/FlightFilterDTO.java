package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightFilterDTO {
    private String departure;
    private String destination;
    private Integer airplaneId;
    private String flightNumber;
}
