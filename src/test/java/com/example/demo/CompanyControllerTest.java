package com.example.demo;

import com.example.demo.controller.CompanyController;
import com.example.demo.service.CompanyService;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.exceptions.NoSuchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CompanyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllCompanies_ReturnsList() throws Exception {
        List<CompanyDTO> companies = Arrays.asList(new CompanyDTO(1, "Company A"), new CompanyDTO(2, "Company B"));

        when(companyService.getAllCompany()).thenReturn(companies);

        mockMvc.perform(get("/api/company/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Company A"));
    }

    @Test
    void getCompanyById_ReturnsCompany() throws Exception {
        CompanyDTO companyDTO = new CompanyDTO(1, "Company A");

        when(companyService.getCompanyById(1)).thenReturn(companyDTO);

        mockMvc.perform(get("/api/company/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Company A"));
    }


    @Test
    void getCompanyById_NotFound() throws Exception {
        when(companyService.getCompanyById(99))
                .thenThrow(new NoSuchException("There is no company with ID = 99 in DB"));

        mockMvc.perform(get("/api/company/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("There is no company with ID = 99 in DB"));
    }



    @Test
    void addCompany_ReturnsCreatedCompany() throws Exception {
        CompanyDTO request = new CompanyDTO(null, "New Company");
        CompanyDTO response = new CompanyDTO(1, "New Company");

        when(companyService.saveCompany(any(CompanyDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/company/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Company"));
    }

    @Test
    void deleteCompany_ReturnsNoContent() throws Exception {
        Mockito.doNothing().when(companyService).deleteCompany(1);

        mockMvc.perform(delete("/api/company/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void changeCompany_ReturnsUpdatedCompany() throws Exception {
        CompanyDTO request = new CompanyDTO(null, "Updated Company");
        CompanyDTO response = new CompanyDTO(1, "Updated Company");

        when(companyService.changeCompany(Mockito.eq(1), any(CompanyDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/company/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Company"));
    }
}