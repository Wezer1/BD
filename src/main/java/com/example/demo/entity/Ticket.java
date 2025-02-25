package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {

    @Id // Определяет поле как первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация значения
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "flight_id", nullable = false)
    private Integer flightId;

    @Column(name = "price", precision = 10, scale = 2, nullable = false) // Точность для BigDecimal
    private BigDecimal price;

    @Column(name = "clas", nullable = false) // Учтите, что "class" является зарезервированным словом в Java
    private String clas;

    @Column(name = "seat", nullable = false)
    private String seat;
}
