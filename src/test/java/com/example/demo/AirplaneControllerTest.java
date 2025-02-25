package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.controller.AirplaneController;
import com.example.demo.dto.AirplaneDTO;
import com.example.demo.service.AirplaneService;
import com.example.demo.exceptions.NoSuchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

public class AirplaneControllerTest {

    @Mock
    private AirplaneService airplaneService;

    @InjectMocks
    private AirplaneController airplaneController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Инициализация моков
        MockitoAnnotations.openMocks(this);
        // Настройка MockMvc
        this.mockMvc = MockMvcBuilders.standaloneSetup(airplaneController).build();
    }

    @Test
    public void testGetAllAirplanes() throws Exception {
        List<AirplaneDTO> airplanes = new ArrayList<>();
        airplanes.add(new AirplaneDTO(1, "Boeing", 200, "Commercial", 1));

        // given
        given(airplaneService.getAllAirplanes()).willReturn(airplanes);

        // when
        // then
        this.mockMvc.perform(get("/api/airplanes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].brand").value("Boeing"));
    }

    @Test
    public void testGetAirplaneById() throws Exception {
        AirplaneDTO airplane = new AirplaneDTO(1, "Boeing", 200, "Commercial", 1);

        // given
        given(airplaneService.getAirplaneById(1)).willReturn(airplane);

        // when
        // then
        this.mockMvc.perform(get("/api/airplanes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand").value("Boeing"));
    }

    @Test
    public void testGetAirplaneById_NotFound() throws Exception {
        // given
        given(airplaneService.getAirplaneById(1)).willThrow(new NoSuchException("Not Found"));

        // when
        // then
        this.mockMvc.perform(get("/api/airplanes/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveAirplane() throws Exception {
        AirplaneDTO airplane = new AirplaneDTO(1, "Boeing", 200, "Commercial", 1);

        // given
        given(airplaneService.saveAirplane(any())).willReturn(airplane);

        // when
        // then
        this.mockMvc.perform(post("/api/airplanes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brand\":\"Boeing\",\"capacity\":200,\"type\":\"Commercial\",\"companyId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand").value("Boeing"));
    }

    @Test
    public void testChangeAirplane() throws Exception {
        AirplaneDTO airplane = new AirplaneDTO(1, "Boeing", 200, "Commercial", 1);

        // given
        given(airplaneService.changeAirplane(eq(1), any())).willReturn(airplane);

        // when
        // then
        this.mockMvc.perform(put("/api/airplanes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brand\":\"Boeing Updated\",\"capacity\":200,\"type\":\"Commercial\",\"companyId\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand").value("Boeing Updated"));
    }

    @Test
    public void testDeleteAirplane() throws Exception {
        Integer airplaneId = 1;

        // given
        doNothing().when(airplaneService).deleteAirplane(airplaneId);

        // when
        // then
        this.mockMvc.perform(delete("/api/airplanes/" + airplaneId))
                .andExpect(status().isNoContent());
    }
}
