package com.example.demo.service;

import com.example.demo.dto.AirplaneDTO;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.Airplane;
import com.example.demo.entity.Company;
import com.example.demo.exceptions.NoSuchException;
import com.example.demo.mapper.AirplaneMapper;
import com.example.demo.repository.AirplaneRepository;
import com.example.demo.repository.CompanyRepository;
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
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final AirplaneMapper airplaneMapper;
    private final CompanyRepository companyRepository;

    @Transactional
    public List<AirplaneDTO> getAllAirplanes() {
        log.info("Get all airplanes");
        if (airplaneRepository.findAll().isEmpty()) {
            throw new NoSuchException("No airplanes");
        }
        return airplaneRepository.findAll().stream().map(airplaneMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public AirplaneDTO getAirplaneById(Integer airplaneId) {
        log.info("Get airplane by id: {} ", airplaneId);
        Optional<Airplane> airplaneOptional = Optional.ofNullable(airplaneRepository.findById(airplaneId)
                .orElseThrow(() -> new NoSuchException("There is no airplane with ID = " + airplaneId + " in DB")));
        return airplaneMapper.toDto(airplaneOptional.get());
    }

    @Transactional
    public AirplaneDTO saveAirplane(AirplaneDTO airplaneDTO) {
        log.info("Saving airplane: {}", airplaneDTO);
        if (companyRepository.findById(airplaneDTO.getCompanyId()).isEmpty()) {
            throw new NoSuchException("There is no company with ID = " + airplaneDTO.getCompanyId() + " in Database");
        } else {
            Airplane savedAirplane = airplaneRepository.save(airplaneMapper.toEntity(airplaneDTO));

            return airplaneMapper.toDto(savedAirplane);
        }
    }

    @Transactional
    public AirplaneDTO changeAirplane(Integer airplaneId, AirplaneDTO airplaneDTO) {
        Optional<Airplane> optionalAirplane = airplaneRepository.findById(airplaneId);
        if (optionalAirplane.isEmpty()) {
            throw new NoSuchException("There is no airplane with ID = " + airplaneId + " in Database");
        } else if (companyRepository.findById(airplaneDTO.getCompanyId()).isEmpty()) {
            throw new NoSuchException("There is no company with ID = " + airplaneDTO.getCompanyId() + " in Database");
        } else {
            Airplane existingAirplane = optionalAirplane.get();

            // Обновляем все поля существующего самолета
            existingAirplane.setBrand(airplaneDTO.getBrand());
            existingAirplane.setCapacity(airplaneDTO.getCapacity());
            existingAirplane.setType(airplaneDTO.getType());
            existingAirplane.setCompanyId(airplaneDTO.getCompanyId());

            // Сохраняем обновленный самолет
            Airplane updatedAirplane = airplaneRepository.save(existingAirplane);

            // Возвращаем обновленный объект в формате DTO
            return airplaneMapper.toDto(updatedAirplane);
        }
    }


    @Transactional
    public void deleteAirplane(Integer airplaneId) {
        log.info("Delete airplane");
        if (airplaneRepository.findById(airplaneId).isEmpty()) {
            throw new NoSuchException("There is no airplane with ID = " + airplaneId + " in Database");
        }
        airplaneRepository.deleteById(airplaneId);
    }

}
