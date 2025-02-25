package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    private Integer id;

    private Integer userId;

    private Integer flightId;

    private BigDecimal price;

    private String clas;

    private String seat;
}
