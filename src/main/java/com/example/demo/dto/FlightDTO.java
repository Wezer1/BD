package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    private Integer id;

    private String departure;

    private String destination;

    private Integer airplaneId;

    private String flightNumber;
}
