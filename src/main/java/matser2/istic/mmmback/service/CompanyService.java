package matser2.istic.mmmback.service;


import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;



    public Company createCompany(Company company) {
        if (company == null || company.getName() == null || company.getName().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'entreprise ne peut pas être vide");
        }
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
