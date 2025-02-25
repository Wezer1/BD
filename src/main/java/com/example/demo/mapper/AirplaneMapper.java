package com.example.demo.mapper;

import com.example.demo.dto.AirplaneDTO;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.Airplane;
import com.example.demo.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AirplaneMapper {
    public abstract Airplane toEntity(AirplaneDTO airplaneDTO);

    public abstract AirplaneDTO toDto(Airplane airplane);
}
