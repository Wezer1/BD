package com.example.demo.service;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.entity.Schedule;
import com.example.demo.exceptions.NoSuchException;
import com.example.demo.mapper.ScheduleMapper;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final FlightRepository flightRepository;


    @Transactional
    public List<ScheduleDTO> getAllSchedule() {
        log.info("Get all schedule");
        if (scheduleRepository.findAll().isEmpty()) {
            throw new NoSuchException("No schedule");
        }
        return scheduleRepository.findAll().stream().map(scheduleMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public ScheduleDTO getScheduleById(Integer scheduleId) {
        log.info("Get schedule by id: {} ", scheduleId);
        Optional<Schedule> scheduleOptional = Optional.ofNullable(scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NoSuchException("There is no schedule with ID = " + scheduleId + " in DB")));
        return scheduleMapper.toDto(scheduleOptional.get());
    }

    @Transactional
    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO) {
        log.info("Saving schedule: {}", scheduleDTO);
        if(flightRepository.findById(scheduleDTO.getFlightId()).isEmpty()){
            throw new NoSuchException("There is no flight with ID = "+ scheduleDTO.getFlightId() + " in Database");
        }else {
            Schedule savedSchedule = scheduleRepository.save(scheduleMapper.toEntity(scheduleDTO));
            return scheduleMapper.toDto(savedSchedule);
        }
    }

    @Transactional
    public ScheduleDTO changeSchedule(Integer scheduleId, ScheduleDTO scheduleDTO){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        if(optionalSchedule.isEmpty()){
            throw new NoSuchException("There is no schedule with ID = "+ scheduleId + " in Database");
        }else if(flightRepository.findById(scheduleDTO.getFlightId()).isEmpty()){
            throw new NoSuchException("There is no flight with ID = "+ scheduleDTO.getFlightId() + " in Database");
        }else{
            Schedule existingSchedule = optionalSchedule.get();
            Schedule updatedSchedule = scheduleMapper.toEntity(scheduleDTO);
            existingSchedule.setArrivalDatetime(updatedSchedule.getArrivalDatetime());
            existingSchedule.setDepartureDatetime(updatedSchedule.getDepartureDatetime());
            existingSchedule.setFlightId(updatedSchedule.getFlightId());
            existingSchedule.setFlightDuration(updatedSchedule.getFlightDuration());

            return scheduleMapper.toDto(scheduleRepository.save(existingSchedule));
        }
    }

    @Transactional
    public void deleteSchedule(Integer scheduleId) {
        log.info("Delete schedule");
        if (scheduleRepository.findById(scheduleId).isEmpty()) {
            throw new NoSuchException("There is no schedule with ID = " + scheduleId + " in Database");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
