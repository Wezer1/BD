package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "airplane")
@Data
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "type")
    private String type;

    @Column(name = "company_id", nullable = false)
    private Integer companyId;
}
