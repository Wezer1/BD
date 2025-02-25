package com.example.demo.mapper;

import com.example.demo.dto.FlightDTO;
import com.example.demo.entity.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FlightMapper {
    public abstract Flight toEntity(FlightDTO flightDTO);

    public abstract FlightDTO toDto(Flight flight);
}
