package matser2.istic.mmmback.controllers;


import jakarta.persistence.EntityNotFoundException;
import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.mappers.ResourcesMapper;
import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Vehicle;
import matser2.istic.mmmback.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1/resources")
public class ResourcesController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourcesMapper resourceMapper;

    // Get all resources
    @GetMapping
    public ResponseEntity<List<ResourcesDto>> getResources() {
        List<ResourcesDto> resourcesDTOs = resourceService.getAllResources();
        if (resourcesDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resourcesDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourcesDto> getResource(@PathVariable Long id) {
        ResourcesDto resourceDto = resourceService.getResourceById(id);
        if (resourceDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(resourceDto);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            Employee employeeEntity = resourceMapper.employeeDtoToEmployee(employeeDto);
            Employee createdEmployee = resourceService.createResource(employeeEntity);
            EmployeeDto createdEmployeeDTO = resourceMapper.employeeToEmployeeDto(createdEmployee);

            URI location = URI.create("/resources/employees/" + createdEmployeeDTO.getId());
            return ResponseEntity.created(location).body(createdEmployeeDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Internal Server Error
        }
    }

    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto) {
        try {
            Vehicle vehicleEntity = resourceMapper.vehicleDtoToVehicle(vehicleDto);
            Vehicle createdVehicle = resourceService.createResource(vehicleEntity);
            VehicleDto createdVehicleDto = resourceMapper.vehicleToVehicleDto(createdVehicle);

            URI location = URI.create("/resources/vehicles/" + createdVehicleDto.getId());
            return ResponseEntity.created(location).body(createdVehicleDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/equipment")
    public ResponseEntity<EquipmentDto> createEquipment(@RequestBody EquipmentDto equipmentDto) {
        try {
            Equipment equipmentEntity = resourceMapper.equipmentDtoToEquipment(equipmentDto);
            Equipment createdEquipment = resourceService.createResource(equipmentEntity);
            EquipmentDto createdEquipmentDto = resourceMapper.equipmentToEquipmentDto(createdEquipment);

            URI location = URI.create("/resources/equipment/" + createdEquipmentDto.getId());
            return ResponseEntity.created(location).body(createdEquipmentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Delete a resource by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        try {
            resourceService.deleteResource(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        try {
            Resources existingResource = resourceService.findById(id);
            if (!(existingResource instanceof Employee)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The resource with ID " + id + " is not an employee.");
            }
            Resources updated = resourceService.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee with ID " + id + " not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle) {
        try {
            Resources existingResource = resourceService.findById(id);
            if (!(existingResource instanceof Vehicle)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The resource with ID " + id + " is not a vehicle.");
            }
            Resources updated = resourceService.updateVehicle(id, updatedVehicle);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vehicle with ID " + id + " not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/equipment/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
        try {
            Resources existingResource = resourceService.findById(id);
            if (!(existingResource instanceof Equipment)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The resource with ID " + id + " is not an equipment.");
            }
            Resources updated = resourceService.updateEquipment(id, updatedEquipment);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Equipment with ID " + id + " not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeSummaryDto>> getAllEmployees() {
        List<EmployeeSummaryDto> employees = resourceService.getAllEmployees();
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleSummaryDto>> getAllVehicles() {
        List<VehicleSummaryDto> vehicles = resourceService.getAllVehicles();
        if (vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/equipment")
    public ResponseEntity<List<EquipmentSummaryDto>> getAllEquipment() {
        List<EquipmentSummaryDto> equipment = resourceService.getAllEquipment();
        if (equipment.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipment);
    }

    @GetMapping("/site-managers")
    public ResponseEntity<List<EmployeeSummaryDto>> getAllSiteManagers() {
        List<EmployeeSummaryDto> siteManagers = resourceService.getAllSiteManagers();
        if (siteManagers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(siteManagers);
    }

    @GetMapping("/teamnates")
    public ResponseEntity<List<EmployeeSummaryDto>> getEquipiers() {
        List<EmployeeSummaryDto> siteManagers = resourceService.getAllEquipiers();
        if (siteManagers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(siteManagers);
    }


    /**
     * Endpoint pour récupérer tous les employés disponibles pour un chantier donné.
     */
    @GetMapping("/available-employees-for-worksite/{worksiteId}")
    public ResponseEntity<List<EmployeeSummaryDto>> getAvailableEmployeesForWorksite(
            @PathVariable Long worksiteId) {
        List<EmployeeSummaryDto> employees = resourceService.getAvailableEmployeesForWorksite(worksiteId);
        return ResponseEntity.ok(employees);
    }

    /**
     * Endpoint pour récupérer tous les véhicules disponibles pour un chantier donné.
     */
    @GetMapping("/available-vehicles-for-worksite/{worksiteId}")
    public ResponseEntity<List<VehicleSummaryDto>> getAvailableVehiclesForWorksite(
            @PathVariable Long worksiteId) {
        List<VehicleSummaryDto> vehicles = resourceService.getAvailableVehiclesForWorksite(worksiteId);
        return ResponseEntity.ok(vehicles);
    }

    /**
     * Endpoint pour récupérer tous les équipements disponibles pour un chantier donné.
     */
    @GetMapping("/available-equipments-for-worksite/{worksiteId}")
    public ResponseEntity<List<EquipmentSummaryDto>> getAvailableEquipmentsForWorksite(
            @PathVariable Long worksiteId) {
        List<EquipmentSummaryDto> equipments = resourceService.getAvailableEquipmentsForWorksite(worksiteId);
        return ResponseEntity.ok(equipments);
    }

    /**
     * Endpoint pour récupérer tous les chefs de chantier disponibles pour un chantier donné.
     */
    @GetMapping("/available-site-managers-for-worksite/{worksiteId}")
    public ResponseEntity<List<EmployeeSummaryDto>> getAvailableSiteManagersForWorksite(
            @PathVariable Long worksiteId) {
        List<EmployeeSummaryDto> siteManagers = resourceService.getAvailableSiteManagersForWorksite(worksiteId);
        return ResponseEntity.ok(siteManagers);
    }

    /**
     * Endpoint pour récupérer tous les équipiers disponibles pour un chantier donné.
     */
    @GetMapping("/available-equipiers-for-worksite/{worksiteId}")
    public ResponseEntity<List<EmployeeSummaryDto>> getAvailableEquipiersForWorksite(
            @PathVariable Long worksiteId) {
        List<EmployeeSummaryDto> equipiers = resourceService.getAvailableEquipiersForWorksite(worksiteId);
        return ResponseEntity.ok(equipiers);
    }

    /**
     * Endpoint pour récupérer toutes les ressources disponibles pour un chantier donné.
     */
    @GetMapping("/available-for-worksite/{worksiteId}")
    public ResponseEntity<List<ResourcesSimpleDto>> getAvailableResourcesForWorksite(
            @PathVariable Long worksiteId) {
        List<ResourcesSimpleDto> resources = resourceService.getAvailableResourcesForWorksite(worksiteId);
        return ResponseEntity.ok(resources);
    }


}
