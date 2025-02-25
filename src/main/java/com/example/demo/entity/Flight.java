package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "flight")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "departure", nullable = false)
    private String departure;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "airplane_id", nullable = false)
    private Integer airplaneId; // Предполагается, что у вас есть класс Airplane

    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;
}

