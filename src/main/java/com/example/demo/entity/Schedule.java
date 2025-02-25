package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
@Entity
@Table(name = "schedule")
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "arrival_datetime", nullable = false)
    private LocalDateTime arrivalDatetime;

    @Column(name = "departure_datetime", nullable = false)
    private LocalDateTime departureDatetime;

    @Column(name = "flight_duration")
    private Long flightDuration; // Вычисляемое поле

    @JoinColumn(name = "flight_id", nullable = false)
    private Integer flightId; // Предполагается, что у вас есть класс Flight

    /**
     * Метод, который будет вызван перед сохранением сущности в базу данных.
     */
    @PrePersist
    protected void prePersist() {
        calculateFlightDuration();
    }

    /**
     * Метод, который будет вызван перед обновлением сущности в базе данных.
     */
    @PreUpdate
    protected void preUpdate() {
        calculateFlightDuration();
    }

    /**
     * Метод для вычисления длительности полета.
     */
    private void calculateFlightDuration() {
        if (arrivalDatetime != null && departureDatetime != null) {
            Duration duration = Duration.between(departureDatetime, arrivalDatetime);
            flightDuration = duration.toMinutes(); // Можно также использовать duration.toMinutes() или duration.toHours()
        } else {
            flightDuration = null; // Если даты не заданы, устанавливаем null
        }
    }


}
