package matser2.istic.mmmback.service;


import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.CompanyAllDto;
import matser2.istic.mmmback.DTO.CompanyGetDto;
import matser2.istic.mmmback.DTO.CompanyPostDto;
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
    /**
     * Crée une nouvelle entreprise à partir d'un DTO.
     *
     * @param companyDto DTO contenant les informations de l'entreprise.
     * @return Le DTO de l'entreprise créée.
     */
    @Transactional
    public CompanyPostDto createCompany(CompanyPostDto companyDto) {
        if (companyDto == null ) {
            throw new IllegalArgumentException("Company's name cannot empty ");
        }
        Company companyEntity = companyMapper.companyPostDtoToCompany(companyDto);
        System.out.println(companyEntity);
        Company savedCompany = companyRepository.save(companyEntity);

        return companyMapper.companyToCompanyPostDto(savedCompany);
    }

    /**
     * Récupère toutes les entreprises.
     *
     * @return Une liste de DTOs représentant les entreprises.
     */
    @Transactional
    public List<CompanyGetDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();

        return companies.stream()
                .map(companyMapper::companyToCompanyGetDto)
                .toList();
    }

    /**
     * Récupère une entreprise par son ID.
     *
     * @param id L'ID de l'entreprise à récupérer.
     * @return Le Dto de l'entreprise ou null si elle n'existe pas.
     */
    @Transactional
    public CompanyGetDto getCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        return company.map(companyMapper::companyToCompanyGetDto).orElse(null);
    }


    @Transactional
    public CompanyPostDto updateCompany(CompanyPostDto companyPostDto) {
        if (companyPostDto.getId() == null) {
            throw new IllegalArgumentException("L'ID de l'entreprise est requis pour la mise à jour.");
        }

        Company existingCompany = companyRepository.findById(companyPostDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("L'entreprise avec l'ID " + companyPostDto.getId() + " n'existe pas."));

        existingCompany.setName(companyPostDto.getName());
        existingCompany.setRegistrationNumber(companyPostDto.getRegistrationNumber());
        existingCompany.setAddress(companyPostDto.getAddress());

        Company updatedCompany = companyRepository.save(existingCompany);

        return companyMapper.companyToCompanyPostDto(updatedCompany);
    }


    /**
     * Supprime une entreprise par son ID.
     *
     * @param id L'ID de l'entreprise à supprimer.
     */
    @Transactional
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("L'entreprise avec l'ID " + id + " n'existe pas."));

        for (Worksite worksite : company.getWorksiteList()) {
            worksite.setCompany(null);
        }
        company.getWorksiteList().clear();

        for (Resources resource : company.getResources()) {
            resource.setCompany(null);
        }
        company.getResources().clear();

        companyRepository.delete(company);
    }

}
