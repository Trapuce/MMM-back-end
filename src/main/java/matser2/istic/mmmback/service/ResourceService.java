package matser2.istic.mmmback.service;


import jakarta.transaction.Transactional;
import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.repository.CompanyRepository;
import matser2.istic.mmmback.repository.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ResourceService {

    @Autowired
    private ResourcesRepository resourcesRepository;
    @Autowired
    private CompanyRepository companyRepository;


    public <T extends Resources> T createResource(T resource) {
        if (resource.getCompany() == null) {
            throw new IllegalArgumentException("Company must be set for the resource.");
        }

        Company company = companyRepository.findById(resource.getCompany().getId())
                .orElseThrow(null);

        company.addResource(resource);
        return resourcesRepository.save(resource);
    }
    public List<Resources> getAllResources() {
        return resourcesRepository.findAll();
    }

    public Resources getResourceById(Long id) {
        return resourcesRepository.findById(id).orElse(null);
    }
}
