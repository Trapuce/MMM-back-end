package matser2.istic.mmmback.service;


import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.CompanyDto;
import matser2.istic.mmmback.mappers.CompanyMapper;
import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Worksite;
import matser2.istic.mmmback.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private CompanyMapper companyMapper;

    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto) {
        if (companyDto == null ) {
            throw new IllegalArgumentException("Company's name cannot empty ");
        }
        Company companyEntity = companyMapper.companyDtoToCompany(companyDto);
        System.out.println(companyEntity);
        Company savedCompany = companyRepository.save(companyEntity);

        return companyMapper.companyToCompanyDto(savedCompany);
    }


    @Transactional
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();

        return companies.stream()
                .map(companyMapper::companyToCompanyDto)
                .toList();
    }


    @Transactional
    public CompanyDto getCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        return company.map(companyMapper::companyToCompanyDto).orElse(null);
    }


    @Transactional
    public CompanyDto updateCompany(CompanyDto companyPostDto) {
        if (companyPostDto.getId() == null) {
            throw new IllegalArgumentException("The company ID is required for the update.");
        }

        Company existingCompany = companyRepository.findById(companyPostDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("L'entreprise avec l'ID " + companyPostDto.getId() + " n'existe pas."));

        existingCompany.setName(companyPostDto.getName());
        existingCompany.setRegistrationNumber(companyPostDto.getRegistrationNumber());
        existingCompany.setAddress(companyPostDto.getAddress());

        Company updatedCompany = companyRepository.save(existingCompany);

        return companyMapper.companyToCompanyDto(updatedCompany);
    }



    @Transactional
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("L'entreprise avec l'ID " + id + " n'existe pas."));



        companyRepository.delete(company);
    }

}
