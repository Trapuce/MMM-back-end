package matser2.istic.mmmback.service;


import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.ResourcesDto;
import matser2.istic.mmmback.mappers.ResourcesMapper;
import matser2.istic.mmmback.mappers.WorksiteMapper;
import matser2.istic.mmmback.models.*;
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
    public Resources findById(Long id) {
        return resourcesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource not found with id: " + id));
    }

    public Resources updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = (Employee) findById(id);
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setPassword(updatedEmployee.getPassword());
        existingEmployee.setRole(updatedEmployee.getRole());
        return resourcesRepository.save(existingEmployee);
    }

    public Resources updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle existingVehicle = (Vehicle) findById(id);
        existingVehicle.setName(updatedVehicle.getName());
        existingVehicle.setLicensePlate(updatedVehicle.getLicensePlate());
        existingVehicle.setModel(updatedVehicle.getModel());
        return resourcesRepository.save(existingVehicle);
    }

    public Resources updateEquipment(Long id, Equipment updatedEquipment) {
        Equipment existingEquipment = (Equipment) findById(id);
        existingEquipment.setName(updatedEquipment.getName());
        existingEquipment.setSerialNumber(updatedEquipment.getSerialNumber());
        existingEquipment.setType(updatedEquipment.getType());
        return resourcesRepository.save(existingEquipment);
    }

    public void deleteResource(Long id) {
        Resources resource = resourcesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ressource non trouvée avec l'ID fourni."));

        Company company = resource.getCompany();
        if (company != null) {
            company.removeResource(resource);
        }

        resourcesRepository.delete(resource);
    }

}
