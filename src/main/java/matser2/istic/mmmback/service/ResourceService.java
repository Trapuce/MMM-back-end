package matser2.istic.mmmback.service;


import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.ResourcesDto;
import matser2.istic.mmmback.mappers.ResourcesMapper;
import matser2.istic.mmmback.mappers.WorksiteMapper;
import matser2.istic.mmmback.models.*;
import matser2.istic.mmmback.repository.AvailabilityRepository;
import matser2.istic.mmmback.repository.CompanyRepository;
import matser2.istic.mmmback.repository.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceService  {

    @Autowired
    private ResourcesRepository resourcesRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Autowired
    private WorksiteMapper worksiteMapper;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public <T extends Resources> T createResource(T resource) {


        T savedResource = resourcesRepository.save(resource);

        if (resource.getAvailabilities() != null && !resource.getAvailabilities().isEmpty()) {
            for (Availability availability : resource.getAvailabilities()) {
                availability.setResource(savedResource);
                availabilityRepository.save(availability);
            }
        }

        if (resource.getAvailabilities() == null || resource.getAvailabilities().isEmpty()) {
            Availability newAvailability = new Availability();
            newAvailability.setResource(savedResource);
            newAvailability.setAvailable(true);
            newAvailability.setStartTime(new Date());
            newAvailability.setEndTime(new Date());
            availabilityRepository.save(newAvailability);
        }

        return savedResource;
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


        resourcesRepository.delete(resource);
    }

}
