package matser2.istic.mmmback.service;


import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.repository.ComapnyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private ComapnyRepository comapnyRepository;



    public void CreateCompany(Company company) {
        comapnyRepository.save(company);
    }
}
