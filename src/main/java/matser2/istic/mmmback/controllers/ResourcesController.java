package matser2.istic.mmmback.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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
@Tag(name = "Resources Management", description = "Endpoints for managing resources like employees, vehicles, and equipment.")
public class ResourcesController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourcesMapper resourceMapper;

    /**
     * Fetch all resources (employees, vehicles, and equipment).
     *
     * @return List of all resources or 204 if none found.
     */
    @GetMapping
    @Operation(summary = "Get all resources", description = "Fetch all resources (employees, vehicles, equipment).")
    @ApiResponse(responseCode = "200", description = "Resources fetched successfully")
    @ApiResponse(responseCode = "204", description = "No resources found")
    public ResponseEntity<List<ResourcesDto>> getResources() {
        List<ResourcesDto> resourcesDTOs = resourceService.getAllResources();
        if (resourcesDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resourcesDTOs);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get resource by ID", description = "Fetch a specific resource by its ID.")
    @ApiResponse(responseCode = "200", description = "Resource fetched successfully")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    public ResponseEntity<ResourcesDto> getResource(@PathVariable Long id) {
        ResourcesDto resourceDto = resourceService.getResourceById(id);

        return ResponseEntity.ok(resourceDto);


    }

    /**
     * Create a new employee resource.
     *
     * @param employeeDto Employee details
     * @return Created employee and location header.
     */
    @PostMapping("/employees")
    @Operation(summary = "Create a new employee", description = "Add a new employee to the system.")
    @ApiResponse(responseCode = "201", description = "Employee created successfully")
    public ResponseEntity<EmployeeDto> createEmployee( @RequestBody @Valid EmployeeDto employeeDto) {

            Employee employeeEntity = resourceMapper.employeeDtoToEmployee(employeeDto);
            Employee createdEmployee = resourceService.createResource(employeeEntity);
            EmployeeDto createdEmployeeDTO = resourceMapper.employeeToEmployeeDto(createdEmployee);

            URI location = URI.create("/resources/employees/" + createdEmployeeDTO.getId());
            return ResponseEntity.created(location).body(createdEmployeeDTO);

    }


    /**
     * Create a new vehicle resource.
     *
     * @param vehicleDto Vehicle details
     * @return Created vehicle and location header.
     */
    @PostMapping("/vehicles")
    @Operation(summary = "Create a new vehicle", description = "Add a new vehicle to the system.")
    @ApiResponse(responseCode = "201", description = "Vehicle created successfully")
    public ResponseEntity<VehicleDto> createVehicle( @RequestBody @Valid VehicleDto vehicleDto) {
            Vehicle vehicleEntity = resourceMapper.vehicleDtoToVehicle(vehicleDto);
            Vehicle createdVehicle = resourceService.createResource(vehicleEntity);
            VehicleDto createdVehicleDto = resourceMapper.vehicleToVehicleDto(createdVehicle);

            URI location = URI.create("/resources/vehicles/" + createdVehicleDto.getId());
            return ResponseEntity.created(location).body(createdVehicleDto);

    }



    /**
     * Create a new equipment resource.
     *
     * @param equipmentDto Equipment details
     * @return Created equipment and location header.
     */
    @PostMapping("/equipment")
    @Operation(summary = "Create a new equipment", description = "Add a new equipment to the system.")
    @ApiResponse(responseCode = "201", description = "Equipment created successfully")
    public ResponseEntity<EquipmentDto> createEquipment( @RequestBody @Valid EquipmentDto equipmentDto) {

            Equipment equipmentEntity = resourceMapper.equipmentDtoToEquipment(equipmentDto);
            Equipment createdEquipment = resourceService.createResource(equipmentEntity);
            EquipmentDto createdEquipmentDto = resourceMapper.equipmentToEquipmentDto(createdEquipment);

            URI location = URI.create("/resources/equipment/" + createdEquipmentDto.getId());
            return ResponseEntity.created(location).body(createdEquipmentDto);

    }


    /**
     * Delete a resource by its ID.
     *
     * @param id Resource ID
     * @return No content on successful deletion.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a resource", description = "Remove a resource (employee, vehicle, equipment) by its ID.")
    @ApiResponse(responseCode = "204", description = "Resource deleted successfully")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
            resourceService.deleteResource(id);
            return ResponseEntity.noContent().build();
    }



    /**
     * Update an employee resource.
     *
     * @param id Employee ID
     * @param updatedEmployee Updated employee details
     * @return Updated employee or error if resource type is invalid.
     */
    @PutMapping("/employees/{id}")
    @Operation(summary = "Update an employee", description = "Modify an existing employee's details.")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully")
    @ApiResponse(responseCode = "400", description = "The resource is not an employee")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {

            Resources existingResource = resourceService.findById(id);
            if (!(existingResource instanceof Employee)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The resource with ID " + id + " is not an employee.");
            }
            Resources updated = resourceService.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(updated);

    }


    /**
     * Update a vehicle resource.
     *
     * @param id Vehicle ID
     * @param updatedVehicle Updated vehicle details
     * @return Updated vehicle or error if resource type is invalid.
     */
    @PutMapping("/vehicles/{id}")
    @Operation(summary = "Update a vehicle", description = "Modify an existing vehicle's details.")
    @ApiResponse(responseCode = "200", description = "Vehicle updated successfully")
    @ApiResponse(responseCode = "400", description = "The resource is not a vehicle")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle) {
            Resources existingResource = resourceService.findById(id);
            if (!(existingResource instanceof Vehicle)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The resource with ID " + id + " is not a vehicle.");
            }
            Resources updated = resourceService.updateVehicle(id, updatedVehicle);
            return ResponseEntity.ok(updated);

    }


    @PutMapping("/equipment/{id}")
    @Operation(summary = "Update equipment", description = "Modify an existing equipment's details.")
    @ApiResponse(responseCode = "200", description = "Equipment updated successfully")
    @ApiResponse(responseCode = "400", description = "The resource is not an equipment")
    public ResponseEntity<?> updateEquipment(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
            Resources existingResource = resourceService.findById(id);
            if (!(existingResource instanceof Equipment)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The resource with ID " + id + " is not an equipment.");
            }
            Resources updated = resourceService.updateEquipment(id, updatedEquipment);
            return ResponseEntity.ok(updated);

    }

    /**
     * Fetch all employees.
     *
     * @return List of all employees.
     */
    @GetMapping("/employees")
    @Operation(summary = "Get all employees", description = "Fetch all employees.")
    @ApiResponse(responseCode = "200", description = "Employees fetched successfully")
    @ApiResponse(responseCode = "204", description = "No employees found")
    public ResponseEntity<List<EmployeeSummaryDto>> getAllEmployees() {
        List<EmployeeSummaryDto> employees = resourceService.getAllEmployees();
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }


    /**
     * Fetch all vehicles.
     *
     * @return List of all vehicles.
     */
    @GetMapping("/vehicles")
    @Operation(summary = "Get all vehicles", description = "Fetch all vehicles.")
    @ApiResponse(responseCode = "200", description = "Vehicles fetched successfully")
    @ApiResponse(responseCode = "204", description = "No vehicles found")
    public ResponseEntity<List<VehicleSummaryDto>> getAllVehicles() {
        List<VehicleSummaryDto> vehicles = resourceService.getAllVehicles();
        if (vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehicles);
    }


    /**
     * Fetch all equipment.
     *
     * @return List of all equipment.
     */
    @GetMapping("/equipment")
    @Operation(summary = "Get all equipment", description = "Fetch all equipment.")
    @ApiResponse(responseCode = "200", description = "Equipment fetched successfully")
    @ApiResponse(responseCode = "204", description = "No equipment found")
    public ResponseEntity<List<EquipmentSummaryDto>> getAllEquipment() {
        List<EquipmentSummaryDto> equipment = resourceService.getAllEquipment();
        if (equipment.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipment);
    }


    /**
     * Fetch all site managers.
     *
     * @return List of all site managers.
     */
    @GetMapping("/site-managers")
    @Operation(summary = "Get all site managers", description = "Fetch all site managers (employees assigned as site managers).")
    @ApiResponse(responseCode = "200", description = "Site managers fetched successfully")
    @ApiResponse(responseCode = "204", description = "No site managers found")
    public ResponseEntity<List<EmployeeSummaryDto>> getAllSiteManagers() {
        List<EmployeeSummaryDto> siteManagers = resourceService.getAllSiteManagers();
        if (siteManagers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(siteManagers);
    }


    /**
     * Fetch all team members (equipiers).
     *
     * @return List of all team members.
     */
    @GetMapping("/teamnates")
    @Operation(summary = "Get all team members", description = "Fetch all employees assigned as team members (equipiers).")
    @ApiResponse(responseCode = "200", description = "Team members fetched successfully")
    @ApiResponse(responseCode = "204", description = "No team members found")
    public ResponseEntity<List<EmployeeSummaryDto>> getEquipiers() {
        List<EmployeeSummaryDto> siteManagers = resourceService.getAllEquipiers();
        if (siteManagers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(siteManagers);
    }


    /**
     * Fetch available employees for a given worksite.
     *
     * @param worksiteId Worksite ID
     * @return List of available employees.
     */
    @GetMapping("/available-employees-for-worksite/{worksiteId}")
    @Operation(summary = "Get available employees for a worksite", description = "Fetch all employees available for a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Available employees fetched successfully")
    public ResponseEntity<List<EmployeeSummaryDto>> getAvailableEmployeesForWorksite(
            @PathVariable Long worksiteId) {
        List<EmployeeSummaryDto> employees = resourceService.getAvailableEmployeesForWorksite(worksiteId);
        return ResponseEntity.ok(employees);
    }

    /**
     * Fetch available vehicles for a given worksite.
     *
     * @param worksiteId Worksite ID
     * @return List of available vehicles.
     */
    @GetMapping("/available-vehicles-for-worksite/{worksiteId}")
    @Operation(summary = "Get available vehicles for a worksite", description = "Fetch all vehicles available for a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Available vehicles fetched successfully")
    public ResponseEntity<List<VehicleSummaryDto>> getAvailableVehiclesForWorksite(
            @PathVariable Long worksiteId) {
        List<VehicleSummaryDto> vehicles = resourceService.getAvailableVehiclesForWorksite(worksiteId);
        return ResponseEntity.ok(vehicles);
    }

    /**
     * Fetch available equipment for a given worksite.
     *
     * @param worksiteId Worksite ID
     * @return List of available equipment.
     */
    @GetMapping("/available-equipments-for-worksite/{worksiteId}")
    @Operation(summary = "Get available equipment for a worksite", description = "Fetch all equipment available for a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Available equipment fetched successfully")
    public ResponseEntity<List<EquipmentSummaryDto>> getAvailableEquipmentsForWorksite(
            @PathVariable Long worksiteId) {
        List<EquipmentSummaryDto> equipments = resourceService.getAvailableEquipmentsForWorksite(worksiteId);
        return ResponseEntity.ok(equipments);
    }

    /**
     * Fetch available site managers for a given worksite.
     *
     * @param worksiteId Worksite ID
     * @return List of available site managers.
     */
    @GetMapping("/available-site-managers-for-worksite/{worksiteId}")
    @Operation(summary = "Get available site managers for a worksite", description = "Fetch all site managers available for a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Available site managers fetched successfully")
    public ResponseEntity<List<EmployeeSummaryDto>> getAvailableSiteManagersForWorksite(
            @PathVariable Long worksiteId) {
        List<EmployeeSummaryDto> siteManagers = resourceService.getAvailableSiteManagersForWorksite(worksiteId);
        return ResponseEntity.ok(siteManagers);
    }

    /**
     * Fetch available team members (equipiers) for a given worksite.
     *
     * @param worksiteId Worksite ID
     * @return List of available team members.
     */
    @GetMapping("/available-equipiers-for-worksite/{worksiteId}")
    @Operation(summary = "Get available team members for a worksite", description = "Fetch all team members (equipiers) available for a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Available team members fetched successfully")
    public ResponseEntity<List<EmployeeSummaryDto>> getAvailableEquipiersForWorksite(
            @PathVariable Long worksiteId) {
        List<EmployeeSummaryDto> equipiers = resourceService.getAvailableEquipiersForWorksite(worksiteId);
        return ResponseEntity.ok(equipiers);
    }

    /**
     * Fetch available resources for a given worksite.
     *
     * @param worksiteId Worksite ID
     * @return List of available resources.
     */
    @GetMapping("/available-for-worksite/{worksiteId}")
    @Operation(summary = "Get available resources for a worksite", description = "Fetch all available resources for a specific worksite.")
    @ApiResponse(responseCode = "200", description = "Available resources fetched successfully")
    public ResponseEntity<List<ResourcesSimpleDto>> getAvailableResourcesForWorksite(
            @PathVariable Long worksiteId) {
        List<ResourcesSimpleDto> resources = resourceService.getAvailableResourcesForWorksite(worksiteId);
        return ResponseEntity.ok(resources);
    }


}
