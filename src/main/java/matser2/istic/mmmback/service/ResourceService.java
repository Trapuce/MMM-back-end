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
import java.util.Optional;
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



    public ResourcesDto getResourceById(Long id) {
        Optional<Resources> resource = resourcesRepository.findById(id);
        return  resource.map(resourcesMapper::resourcesToResourcesDto).orElse(null);
    }

    public ResourcesDto updateResource(Long id, ResourcesDto resourceDto) {
        Resources existingResource = resourcesRepository.findById(id).get();

        existingResource.setName(resourceDto.getName());

        Company company = companyRepository.findById(resourceDto.getCompany().getId())
                .orElseThrow(() -> new IllegalArgumentException("Société non trouvée avec l'ID fourni."));

        company.addResource(existingResource);
        Resources updatedResource = resourcesRepository.save(existingResource);
        return resourcesMapper.resourcesToResourcesDto(updatedResource);
    }

    public void deleteResource(Long id) {
        Resources resource = resourcesRepository.findById(id).get();
        if(resource == null){
             new IllegalArgumentException ("no ressources");
        }
        resourcesRepository.delete(resource);
    }
}
