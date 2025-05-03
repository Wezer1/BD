package com.example.demo.service;

import com.example.demo.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService {

    private final RestTemplate restTemplate;


    // Получение данных о самолетах
    public List<AirplaneDTO> getAirplanes() {
        String url = "http://localhost:8080/api/airplane/";
        ResponseEntity<AirplaneDTO[]> response = restTemplate.getForEntity(url, AirplaneDTO[].class);
        return Arrays.asList(response.getBody());
    }

    // Получение данных о компаниях
    public List<CompanyDTO> getCompanies() {
        String url = "http://localhost:8080/api/company/";
        ResponseEntity<CompanyDTO[]> response = restTemplate.getForEntity(url, CompanyDTO[].class);
        return Arrays.asList(response.getBody());
    }

    // Получение данных о рейсах
    public List<FlightDTO> getFlights() {
        String url = "http://localhost:8080/api/flight/";
        ResponseEntity<FlightDTO[]> response = restTemplate.getForEntity(url, FlightDTO[].class);
        return Arrays.asList(response.getBody());
    }

    // Получение данных о расписании
    public List<ScheduleDTO> getSchedules() {
        String url = "http://localhost:8080/api/schedule/";
        ResponseEntity<ScheduleDTO[]> response = restTemplate.getForEntity(url, ScheduleDTO[].class);
        return Arrays.asList(response.getBody());
    }

    // Получение данных о билетах
    public List<TicketDTO> getTickets() {
        String url = "http://localhost:8080/api/tickets/";
        ResponseEntity<TicketDTO[]> response = restTemplate.getForEntity(url, TicketDTO[].class);
        return Arrays.asList(response.getBody());
    }

    // Получение данных о пользователях
    public List<UserDTO> getUsers() {
        String url = "http://localhost:8080/api/users/";
        ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(url, UserDTO[].class);
        return Arrays.asList(response.getBody());
    }
}
