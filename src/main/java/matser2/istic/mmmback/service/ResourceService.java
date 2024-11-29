package matser2.istic.mmmback.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.mappers.ResourcesMapper;
import matser2.istic.mmmback.mappers.ResourcesSimpleMapper;
import matser2.istic.mmmback.mappers.WorksiteMapper;
import matser2.istic.mmmback.models.*;
import matser2.istic.mmmback.repository.AvailabilityRepository;
import matser2.istic.mmmback.repository.CompanyRepository;
import matser2.istic.mmmback.repository.ResourcesRepository;
import matser2.istic.mmmback.repository.WorksiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

    @Autowired
    private ResourcesSimpleMapper resourcesSimpleMapper;

    @Autowired
    private WorksiteRepository worksiteRepository;

    public <T extends Resources> T createResource(T resource) {

        // Save the resource first
        T savedResource = resourcesRepository.save(resource);

        if (resource.getAvailabilities() != null && !resource.getAvailabilities().isEmpty()) {
            for (Availability availability : resource.getAvailabilities()) {
                savedResource.addAvailability(availability);
                availabilityRepository.save(availability);
            }
        }

        if (resource.getAvailabilities() == null || resource.getAvailabilities().isEmpty()) {
            Availability newAvailability = new Availability();
            savedResource.addAvailability(newAvailability);
            newAvailability.setStartTime(null);  // Explicitly not set
            newAvailability.setEndTime(null);    // Explicitly not set

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


    /**
     * Récupère toutes les ressources de type Employee.
     */
    public List<EmployeeSummaryDto> getAllEmployees() {
        return resourcesRepository.findAll().stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> resourcesSimpleMapper.employeeToEmployeeSummaryDto((Employee) resource))
                .collect(Collectors.toList());
    }

    /**
     * Récupère toutes les ressources de type Vehicle.
     */
    public List<VehicleSummaryDto> getAllVehicles() {
        return resourcesRepository.findAll().stream()
                .filter(resource -> resource instanceof Vehicle)
                .map(resource -> resourcesSimpleMapper.vehicleToVehicleSummaryDto((Vehicle) resource))
                .collect(Collectors.toList());
    }

    /**
     * Récupère toutes les ressources de type Equipment.
     */
    public List<EquipmentSummaryDto> getAllEquipment() {
        return resourcesRepository.findAll().stream()
                .filter(resource -> resource instanceof Equipment)
                .map(resource -> resourcesSimpleMapper.equipmentToEquipmentSummaryDto((Equipment) resource))
                .collect(Collectors.toList());
    }

    public List<EmployeeSummaryDto> getAllSiteManagers() {
        return resourcesRepository.findAll().stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .filter(employee -> Role.CHEF_DE_CHANTIER.equals(employee.getRole()))
                .map(resourcesSimpleMapper::employeeToEmployeeSummaryDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeSummaryDto> getAllEquipiers() {
        return resourcesRepository.findAll().stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .filter(employee -> Role.EQUIPIER_SIMPLE.equals(employee.getRole()))
                .map(resourcesSimpleMapper::employeeToEmployeeSummaryDto)
                .collect(Collectors.toList());
    }
    public List<EmployeeSummaryDto> getAvailableEmployeesForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        return availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .map(resourcesSimpleMapper::employeeToEmployeeSummaryDto)
                .collect(Collectors.toList());
    }

        public Date calculateEndDate(Date startDate, int durationInHalfDays) {
            int daysToAdd = durationInHalfDays / 2;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);

            return calendar.getTime();
        }

    public List<VehicleSummaryDto> getAvailableVehiclesForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        return availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Vehicle)
                .map(resource -> (Vehicle) resource)
                .map(resourcesSimpleMapper::vehicleToVehicleSummaryDto)
                .collect(Collectors.toList());
    }

    public List<EquipmentSummaryDto> getAvailableEquipmentsForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        // Récupérer les équipements disponibles pendant la période du chantier
        return availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Equipment)
                .map(resource -> (Equipment) resource)
                .map(resourcesSimpleMapper::equipmentToEquipmentSummaryDto)
                .collect(Collectors.toList());
    }
    public List<EmployeeSummaryDto> getAvailableSiteManagersForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        return availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .filter(employee -> Role.CHEF_DE_CHANTIER.equals(employee.getRole()))
                .map(resourcesSimpleMapper::employeeToEmployeeSummaryDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeSummaryDto> getAvailableEquipiersForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        return availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .filter(employee -> Role.EQUIPIER_SIMPLE.equals(employee.getRole()))
                .map(resourcesSimpleMapper::employeeToEmployeeSummaryDto)
                .collect(Collectors.toList());
    }
    public List<ResourcesSimpleDto> getAvailableResourcesForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new EntityNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        return availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .map(resourcesSimpleMapper::resourcesToResourcesSimpleDto)
                .collect(Collectors.toList());
    }

}
