package matser2.istic.mmmback.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.exceptions.ResourceNotAvailableException;
import matser2.istic.mmmback.exceptions.ResourceNotFoundException;
import matser2.istic.mmmback.exceptions.ResourceTypeMismatchException;
import matser2.istic.mmmback.exceptions.ResourceValidationException;
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


    /**
     * Creates a new resource in the system.
     *
     * @param resource The resource to be created
     * @return The saved resource
     * @throws ResourceValidationException if the resource is null
     */
    public <T extends Resources> T createResource(T resource) {
        if (resource == null) {
            throw new ResourceValidationException("resource", "Resource cannot be null");
        }

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
            newAvailability.setStartTime(null);
            newAvailability.setEndTime(null);
            availabilityRepository.save(newAvailability);
        }

        return savedResource;

    }


    /**
     * Retrieves all resources in the system.
     *
     * @return List of all resources as ResourcesDto
     * @throws ResourceNotFoundException if no resources are found
     */
    public List<ResourcesDto> getAllResources() {
        List<Resources> resources = resourcesRepository.findAll();

        if (resources.isEmpty()) {
            throw new ResourceNotFoundException("No resources found");
        }
        return resources.stream()
                .map(resourcesMapper::resourcesToResourcesDto)
                .collect(Collectors.toList());
    }




    /**
     * Retrieves a resource by its ID.
     *
     * @param id The ID of the resource
     * @return ResourcesDto representing the resource
     * @throws ResourceNotFoundException if no resource is found with the given ID
     */
    public ResourcesDto getResourceById(Long id) {
        Optional<Resources> resource = resourcesRepository.findById(id);
        return  resource.map(resourcesMapper::resourcesToResourcesDto).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Finds a resource by its ID and returns the raw resource object.
     *
     * @param id The ID of the resource
     * @return The found Resources object
     * @throws ResourceNotFoundException if no resource is found with the given ID
     */
    public Resources findById(Long id) {
        return resourcesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Updates an existing Employee resource.
     *
     * @param id The ID of the employee to update
     * @param updatedEmployee The updated employee information
     * @return The updated Employee resource
     * @throws ResourceTypeMismatchException if the resource is not an Employee
     */
    public Resources updateEmployee(Long id, Employee updatedEmployee) {
        Resources resource = findById(id);
        if (!(resource instanceof Employee existingEmployee)) {
            throw new ResourceTypeMismatchException(id, "Employee", resource.getClass().getSimpleName());
        }
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setPassword(updatedEmployee.getPassword());
        existingEmployee.setRole(updatedEmployee.getRole());
        return resourcesRepository.save(existingEmployee);
    }

    /**
     * Updates an existing Vehicle resource.
     *
     * @param id The ID of the vehicle to update
     * @param updatedVehicle The updated vehicle information
     * @return The updated Vehicle resource
     * @throws ResourceTypeMismatchException if the resource is not a Vehicle
     */
    public Resources updateVehicle(Long id, Vehicle updatedVehicle) {
        Resources resource = findById(id);
        if (!(resource instanceof Vehicle existingVehicle)) {
            throw new ResourceTypeMismatchException(id, "Vehicle", resource.getClass().getSimpleName());
        }
        existingVehicle.setName(updatedVehicle.getName());
        existingVehicle.setLicensePlate(updatedVehicle.getLicensePlate());
        existingVehicle.setModel(updatedVehicle.getModel());
        return resourcesRepository.save(existingVehicle);
    }


    /**
     * Updates an existing Equipment resource.
     *
     * @param id The ID of the equipment to update
     * @param updatedEquipment The updated equipment information
     * @return The updated Equipment resource
     * @throws ResourceTypeMismatchException if the resource is not an Equipment
     */
    public Resources updateEquipment(Long id, Equipment updatedEquipment) {
        Resources resource = findById(id);
        if (!(resource instanceof Equipment existingEquipment)) {
            throw new ResourceTypeMismatchException(id, "Equipment", resource.getClass().getSimpleName());
        }
        existingEquipment.setName(updatedEquipment.getName());
        existingEquipment.setSerialNumber(updatedEquipment.getSerialNumber());
        existingEquipment.setType(updatedEquipment.getType());
        return resourcesRepository.save(existingEquipment);
    }

    /**
     * Deletes a resource from the system.
     *
     * @param id The ID of the resource to delete
     * @throws ResourceNotFoundException if no resource is found with the given ID
     */
    public void deleteResource(Long id) {
        Resources resource = resourcesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with ID supplied."));

        resourcesRepository.delete(resource);
    }



    /**
     * Retrieves all employees in the system.
     *
     * @return List of EmployeeSummaryDto containing employee details
     */
    public List<EmployeeSummaryDto> getAllEmployees() {
        return resourcesRepository.findAll().stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> resourcesSimpleMapper.employeeToEmployeeSummaryDto((Employee) resource))
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all vehicles in the system.
     *
     * @return List of VehicleSummaryDto containing vehicle details
     */
    public List<VehicleSummaryDto> getAllVehicles() {
        return resourcesRepository.findAll().stream()
                .filter(resource -> resource instanceof Vehicle)
                .map(resource -> resourcesSimpleMapper.vehicleToVehicleSummaryDto((Vehicle) resource))
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all equipment in the system.
     *
     * @return List of EquipmentSummaryDto containing equipment details
     */
    public List<EquipmentSummaryDto> getAllEquipment() {
        return resourcesRepository.findAll().stream()
                .filter(resource -> resource instanceof Equipment)
                .map(resource -> resourcesSimpleMapper.equipmentToEquipmentSummaryDto((Equipment) resource))
                .collect(Collectors.toList());
    }


    /**
     * Retrieves all site managers (employees with CHEF_DE_CHANTIER role).
     *
     * @return List of EmployeeSummaryDto for site managers
     */
    public List<EmployeeSummaryDto> getAllSiteManagers() {
        return resourcesRepository.findAll().stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .filter(employee -> Role.CHEF_DE_CHANTIER.equals(employee.getRole()))
                .map(resourcesSimpleMapper::employeeToEmployeeSummaryDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all simple team members (employees with EQUIPIER_SIMPLE role).
     *
     * @return List of EmployeeSummaryDto for simple team members
     */
    public List<EmployeeSummaryDto> getAllEquipiers() {
        return resourcesRepository.findAll().stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .filter(employee -> Role.EQUIPIER_SIMPLE.equals(employee.getRole()))
                .map(resourcesSimpleMapper::employeeToEmployeeSummaryDto)
                .collect(Collectors.toList());
    }


    /**
     * Finds available employees for a specific worksite.
     *
     * @param worksiteId The ID of the worksite
     * @return List of EmployeeSummaryDto for available employees
     * @throws ResourceNotFoundException if worksite is not found
     * @throws ResourceNotAvailableException if no employees are available
     */
    public List<EmployeeSummaryDto> getAvailableEmployeesForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        List<EmployeeSummaryDto> availableEmployees = availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .map(resourcesSimpleMapper::employeeToEmployeeSummaryDto)
                .collect(Collectors.toList());

//        if (availableEmployees.isEmpty()) {
//            throw new ResourceNotAvailableException("No employees available for worksite " + worksiteId);
//        }

        return availableEmployees;
    }

    /**
     * Calculates the end date based on a start date and duration.
     *
     * @param startDate The start date of the worksite
     * @param durationInHalfDays Duration in half-day increments
     * @return Calculated end date
     */
    public Date calculateEndDate(Date startDate, int durationInHalfDays) {
            int daysToAdd = durationInHalfDays / 2;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
            return calendar.getTime();
    }

    /**
     * Retrieves available vehicles for a specific worksite.
     *
     * @param worksiteId The ID of the worksite
     * @return List of VehicleSummaryDto for available vehicles
     * @throws ResourceNotFoundException if worksite is not found
     * @throws ResourceNotAvailableException if no vehicles are available
     */
    public List<VehicleSummaryDto> getAvailableVehiclesForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        List<VehicleSummaryDto> availableVehicles = availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Vehicle)
                .map(resource -> (Vehicle) resource)
                .map(resourcesSimpleMapper::vehicleToVehicleSummaryDto)
                .collect(Collectors.toList());
//
//        if (availableVehicles.isEmpty()) {
//            throw new ResourceNotAvailableException("No vehicles available for worksite " + worksiteId);
//        }

        return availableVehicles;
    }


    /**
     * Retrieves available equipment for a specific worksite.
     *
     * @param worksiteId The ID of the worksite
     * @return List of EquipmentSummaryDto for available equipment
     * @throws ResourceNotFoundException if worksite is not found
     * @throws ResourceNotAvailableException if no equipment is available
     */
    public List<EquipmentSummaryDto> getAvailableEquipmentsForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        List<EquipmentSummaryDto> availableEquipments= availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Equipment)
                .map(resource -> (Equipment) resource)
                .map(resourcesSimpleMapper::equipmentToEquipmentSummaryDto)
                .collect(Collectors.toList());

//        if (availableEquipments.isEmpty()) {
//            throw new ResourceNotAvailableException("No equipments available for worksite " + worksiteId);
//        }

        return availableEquipments;
    }

    /**
     * Retrieves available site managers for a specific worksite.
     *
     * @param worksiteId The ID of the worksite
     * @return List of EmployeeSummaryDto for available site managers
     * @throws ResourceNotFoundException if worksite is not found
     * @throws ResourceNotAvailableException if no site managers are available
     */
    public List<EmployeeSummaryDto> getAvailableSiteManagersForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        List<EmployeeSummaryDto> availableSiteManagers =  availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .filter(employee -> Role.CHEF_DE_CHANTIER.equals(employee.getRole()))
                .map(resourcesSimpleMapper::employeeToEmployeeSummaryDto)
                .collect(Collectors.toList());
//
//        if (availableSiteManagers.isEmpty()) {
//            throw new ResourceNotAvailableException("No site-Managers available for worksite " + worksiteId);
//        }

        return availableSiteManagers;
    }


    /**
     * Retrieves available team members (equipiers) for a specific worksite.
     *
     * @param worksiteId The ID of the worksite
     * @return List of EmployeeSummaryDto for available team members
     * @throws ResourceNotFoundException if worksite is not found
     * @throws ResourceNotAvailableException if no team members are available
     */
    public List<EmployeeSummaryDto> getAvailableEquipiersForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        List<EmployeeSummaryDto> availableEquipiers =  availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .filter(employee -> Role.EQUIPIER_SIMPLE.equals(employee.getRole()))
                .map(resourcesSimpleMapper::employeeToEmployeeSummaryDto)
                .collect(Collectors.toList());

//        if (availableEquipiers == null) {
//            throw new ResourceNotAvailableException("No simple equipiers available for worksite " + worksiteId);
//        }

        return availableEquipiers;
    }


    /**
     * Retrieves all available resources for a specific worksite.
     *
     * @param worksiteId The ID of the worksite
     * @return List of ResourcesSimpleDto for all available resources
     * @throws ResourceNotFoundException if worksite is not found
     * @throws ResourceNotAvailableException if no resources are available
     */
    public List<ResourcesSimpleDto> getAvailableResourcesForWorksite(Long worksiteId) {
        Worksite worksite = worksiteRepository.findById(worksiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Worksite not found with id: " + worksiteId));

        Date startDate = worksite.getStartDate();
        Date endDate = calculateEndDate(startDate, worksite.getDuration());

        List<ResourcesSimpleDto> availableResources =   availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .map(resourcesSimpleMapper::resourcesToResourcesSimpleDto)
                .collect(Collectors.toList());
//
//        if (availableResources == null) {
//            throw new ResourceNotAvailableException("No resources available for worksite " + worksiteId);
//        }

        return availableResources;
    }

}
