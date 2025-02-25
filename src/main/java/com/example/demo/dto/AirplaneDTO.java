package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirplaneDTO {

    private Integer id;

    private String brand;

    private Integer capacity;

    private String type;

    private Integer companyId;
}
