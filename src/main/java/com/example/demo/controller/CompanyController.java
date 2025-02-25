package com.example.demo.controller;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/")
    public ResponseEntity<List<CompanyDTO>> getBoxes(){
        return ResponseEntity.ok(companyService.getAllCompany());
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDTO> getBoxById(@PathVariable Integer companyId){
        return ResponseEntity.ok(companyService.getCompanyById(companyId));
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<CompanyDTO> deleteClient(@PathVariable Integer companyId){
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<CompanyDTO> addBox(@Valid @RequestBody CompanyDTO companyDTO){
        return ResponseEntity.ok(companyService.saveCompany(companyDTO));
    }

    @PostMapping("/{companyId}")
    public ResponseEntity<CompanyDTO> changeBox(@PathVariable Integer companyId,
                                            @Valid @RequestBody CompanyDTO companyDTO){
        return ResponseEntity.ok(companyService.changeCompany(companyId, companyDTO));
    }
}
