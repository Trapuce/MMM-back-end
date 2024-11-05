package matser2.istic.mmmback.service;


import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.ResourcesDto;
import matser2.istic.mmmback.mappers.ResourcesMapper;
import matser2.istic.mmmback.mappers.WorksiteMapper;
import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.repository.CompanyRepository;
import matser2.istic.mmmback.repository.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceService {

    @Autowired
    private ResourcesRepository resourcesRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Autowired
    private WorksiteMapper worksiteMapper;

    public <T extends Resources> T createResource(T resource) {
        if (resource.getCompany() == null) {
            throw new IllegalArgumentException("La société doit être définie pour la ressource.");
        }

        Company company = companyRepository.findById(resource.getCompany().getId())
                .orElseThrow(() -> new IllegalArgumentException("Société non trouvée avec l'ID fourni."));

        company.addResource(resource);
        return resourcesRepository.save(resource);
    }

    public List<ResourcesDto> getAllResources() {
        List<Resources> resources = resourcesRepository.findAll();
        return resources.stream()
                .map(resourcesMapper::resourcesToResourcesDto)
                .collect(Collectors.toList());
    }

    public Resources getResourceById(Long id) {
        return resourcesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ressource non trouvée avec l'ID fourni."));
    }

    public ResourcesDto getResourceDtoById(Long id) {
        Resources resource = getResourceById(id);
        return resourcesMapper.resourcesToResourcesDto(resource);
    }
}
