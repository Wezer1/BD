package com.example.demo.mapper;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.entity.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ScheduleMapper {
    public abstract Schedule toEntity(ScheduleDTO scheduleDTO);

    public abstract ScheduleDTO toDto(Schedule schedule);
}
