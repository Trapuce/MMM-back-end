package matser2.istic.mmmback.controllers;


import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.mappers.ResourcesMapper;
import matser2.istic.mmmback.models.*;
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

  /*  @GetMapping
    public ResponseEntity<List<ResourcesDto>> getResources() {
        List<ResourcesDto> resourcesDTOs = resourceService.getAllResources();
        if (resourcesDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resourcesDTOs);
    }*/

  /*  @GetMapping("/{id}")
    public ResponseEntity<ResourcesDto> getResource(@PathVariable Long id) {
        Resources resource = resourceService.getResourceById(id);
        if (resource == null) {
            return ResponseEntity.notFound().build();
        }
        ResourcesDto resourceDTO = resourceMapper.resourcesToResourcesDto(resource);
        return ResponseEntity.ok(resourceDTO);
    }*/

    @PostMapping("/employees")
    public ResponseEntity<EmployeeAllDto> createEmployee(@RequestBody EmployeeAllDto employeeDto) {
        Employee employeeEntity = resourceMapper.employeeDtoToEmployee(employeeDto);
        Employee createdEmployee = resourceService.createResource(employeeEntity);
        EmployeeAllDto createdEmployeeDTO = resourceMapper.employeeToEmployeeDto(createdEmployee);

        return ResponseEntity
                .created(URI.create("/api/resources/employees/" + createdEmployeeDTO.getId()))
                .body(createdEmployeeDTO);
    }

    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto) {
        Vehicle vehicleEntity = resourceMapper.vehicleDtoToVehicle(vehicleDto);
        Vehicle createdVehicle = resourceService.createResource(vehicleEntity);
        VehicleDto createdVehicleDto = resourceMapper.vehicleToVehicleDto(createdVehicle);

        return ResponseEntity
                .created(URI.create("/api/resources/vehicles/" + createdVehicleDto.getId()))
                .body(createdVehicleDto);
    }

    @PostMapping("/equipment")
    public ResponseEntity<EquipmentDto> createEquipment(@RequestBody EquipmentDto equipmentDto) {
        Equipment equipmentEntity = resourceMapper.equipmentDtoToEquipment(equipmentDto);
        Equipment createdEquipment = resourceService.createResource(equipmentEntity);
        EquipmentDto createdEquipmentDto = resourceMapper.equipmentToEquipmentDto(createdEquipment);

        return ResponseEntity
                .created(URI.create("/api/resources/equipment/" + createdEquipmentDto.getId()))
                .body(createdEquipmentDto);
    }
 /*   @PostMapping("/supply")
    public ResponseEntity<Supply> addSupply( @RequestBody Supply supply) {
        Supply createdSupply =  resourceService.createResource(supply);
        return ResponseEntity
                .created(URI.create("/api/resources/supply/" + createdSupply.getId()))
                .body(createdSupply);
    }*/
}
