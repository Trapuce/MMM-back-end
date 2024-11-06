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
@RequestMapping("/resources")
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
 /*   @PostMapping("/supply")
    public ResponseEntity<Supply> addSupply( @RequestBody Supply supply) {
        Supply createdSupply =  resourceService.createResource(supply);
        return ResponseEntity
                .created(URI.create("/api/resources/supply/" + createdSupply.getId()))
                .body(createdSupply);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<ResourcesDto> updateResource(@PathVariable Long id, @RequestBody ResourcesDto resourceDto) {
        ResourcesDto updatedResourceDto = resourceService.updateResource(id, resourceDto);
        return ResponseEntity.ok(updatedResourceDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}
