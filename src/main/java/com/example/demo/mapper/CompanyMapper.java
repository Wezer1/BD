package com.example.demo.mapper;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CompanyMapper {
    public abstract Company toEntity(CompanyDTO boxDTO);

    public abstract CompanyDTO toDto(Company box);
}
