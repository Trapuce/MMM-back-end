package matser2.istic.mmmback.controllers;


import matser2.istic.mmmback.DTO.EmployeeDto;
import matser2.istic.mmmback.DTO.EquipmentDto;
import matser2.istic.mmmback.DTO.ResourcesDto;
import matser2.istic.mmmback.DTO.VehicleDto;
import matser2.istic.mmmback.mappers.ResourcesMapper;
import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Vehicle;
import matser2.istic.mmmback.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resources")
public class ResourcesController {

    @Autowired
    private ResourceService resourceService;


    @Autowired
    private ResourcesMapper resourceMapper ;

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
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resourceDto);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employeeEntity = resourceMapper.employeeDtoToEmployee(employeeDto);
        Employee createdEmployee = resourceService.createResource(employeeEntity);
        EmployeeDto createdEmployeeDTO = resourceMapper.employeeToEmployeeDto(createdEmployee);

        return ResponseEntity
                .created(URI.create("/resources/employees/" + createdEmployeeDTO.getId()))
                .body(createdEmployeeDTO);
    }

    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto) {
        Vehicle vehicleEntity = resourceMapper.vehicleDtoToVehicle(vehicleDto);
        Vehicle createdVehicle = resourceService.createResource(vehicleEntity);
        VehicleDto createdVehicleDto = resourceMapper.vehicleToVehicleDto(createdVehicle);

        return ResponseEntity
                .created(URI.create("/resources/vehicles/" + createdVehicleDto.getId()))
                .body(createdVehicleDto);
    }

    @PostMapping("/equipment")
    public ResponseEntity<EquipmentDto> createEquipment(@RequestBody EquipmentDto equipmentDto) {
        Equipment equipmentEntity = resourceMapper.equipmentDtoToEquipment(equipmentDto);
        Equipment createdEquipment = resourceService.createResource(equipmentEntity);
        EquipmentDto createdEquipmentDto = resourceMapper.equipmentToEquipmentDto(createdEquipment);

        return ResponseEntity
                .created(URI.create("/resources/equipment/" + createdEquipmentDto.getId()))
                .body(createdEquipmentDto);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        try {
            Resources existingResource = resourceService.findById(id);
            if (!(existingResource instanceof Employee)) {
                return ResponseEntity.badRequest()
                        .body("La ressource avec l'ID " + id + " n'est pas un employé");
            }
            Resources updated = resourceService.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle) {
        try {
            Resources existingResource = resourceService.findById(id);
            if (!(existingResource instanceof Vehicle)) {
                return ResponseEntity.badRequest()
                        .body("La ressource avec l'ID " + id + " n'est pas un véhicule");
            }
            Resources updated = resourceService.updateVehicle(id, updatedVehicle);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/equipment/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
        try {
            Resources existingResource = resourceService.findById(id);
            if (!(existingResource instanceof Equipment)) {
                return ResponseEntity.badRequest()
                        .body("La ressource avec l'ID " + id + " n'est pas un équipement");
            }
            Resources updated = resourceService.updateEquipment(id, updatedEquipment);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees() {
        return resourceService.getAllEmployees();
    }

    @GetMapping("/vehicles")
    public List<VehicleDto> getAllVehicles() {
        return resourceService.getAllVehicles();
    }

    @GetMapping("/equipment")
    public List<EquipmentDto> getAllEquipment() {
        return resourceService.getAllEquipment();
    }

    @GetMapping("/site-managers")
    public List<EmployeeDto> getAllSiteManagers() {
        return resourceService.getAllSiteManagers();
    }
}



