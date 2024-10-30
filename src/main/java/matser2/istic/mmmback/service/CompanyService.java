package matser2.istic.mmmback.service;


import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.CompanyAllDto;
import matser2.istic.mmmback.DTO.CompanyPostDto;
import matser2.istic.mmmback.mappers.CompanyMapper;
import matser2.istic.mmmback.models.Company;
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
            throw new IllegalArgumentException("Le nom de l'entreprise ne peut pas être vide");
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
    public List<CompanyPostDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();

        return companies.stream()
                .map(companyMapper::companyToCompanyPostDto)
                .toList();
    }

    /**
     * Récupère une entreprise par son ID.
     *
     * @param id L'ID de l'entreprise à récupérer.
     * @return Le Dto de l'entreprise ou null si elle n'existe pas.
     */
    @Transactional
    public CompanyPostDto getCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        // Si l'entreprise est trouvée, la convertir en DTO, sinon retourner null
        return company.map(companyMapper::companyToCompanyPostDto).orElse(null);
    }

    /**
     * Supprime une entreprise par son ID.
     *
     * @param id L'ID de l'entreprise à supprimer.
     */
    @Transactional
    public void deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("L'entreprise avec l'ID " + id + " n'existe pas.");
        }
    }
}
