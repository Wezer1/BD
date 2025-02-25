package com.example.demo.service;


import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.Company;
import com.example.demo.exceptions.NoSuchException;
import com.example.demo.mapper.CompanyMapper;
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
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Transactional
    public List<CompanyDTO> getAllCompany() {
        log.info("Get all company");
        if (companyRepository.findAll().isEmpty()) {
            throw new NoSuchException("No company");
        }
        return companyRepository.findAll().stream().map(companyMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public CompanyDTO getCompanyById(Integer companyId) {
        log.info("Get company by id: {} ", companyId);
        Optional<Company> companyOptional = Optional.ofNullable(companyRepository.findById(companyId)
                .orElseThrow(() -> new NoSuchException("There is no company with ID = " + companyId + " in DB")));
        return companyMapper.toDto(companyOptional.get());
    }

    @Transactional
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        log.info("Saving company: {}", companyDTO);
        Company savedCompany = companyRepository.save(companyMapper.toEntity(companyDTO));
        return companyMapper.toDto(savedCompany);
    }

    @Transactional
    public CompanyDTO changeCompany(Integer companyId, CompanyDTO companyDTO){
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if(optionalCompany.isEmpty()){
            throw new NoSuchException("There is no company with ID = "+ companyId + " in Database");
        }else{
            Company existingCompany = optionalCompany.get();
            Company updatedCompany = companyMapper.toEntity(companyDTO);
            existingCompany.setName(updatedCompany.getName());

            return companyMapper.toDto(companyRepository.save(existingCompany));
        }
    }

    @Transactional
    public void deleteCompany(Integer companyId) {
        log.info("Delete company");
        if (companyRepository.findById(companyId).isEmpty()) {
            throw new NoSuchException("There is no company with ID = " + companyId + " in Database");
        }
        companyRepository.deleteById(companyId);
    }
}
