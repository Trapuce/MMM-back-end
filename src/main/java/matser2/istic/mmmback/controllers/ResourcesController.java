package matser2.istic.mmmback.controllers;


import jakarta.annotation.Resource;
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


    @GetMapping
    public ResponseEntity<List<Resources>> getResources() {
        List<Resources> resources = resourceService.getAllResources();
        if (resources.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resources> getResource(@PathVariable Long id) {
        Resources resource = resourceService.getResourceById(id);
        if (resource == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resource);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee( @RequestBody Employee employee) {
        Employee createdEmployee = resourceService.createResource(employee);
        return ResponseEntity
                .created(URI.create("/api/resources/employees/" + createdEmployee.getId()))
                .body(createdEmployee);
    }

    @PostMapping("/vehicle")
    public ResponseEntity<Vehicle> addVehicle( @RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = resourceService.createResource(vehicle);
        return ResponseEntity
                .created(URI.create("/api/resources/vehicle/" + createdVehicle.getId()))
                .body(createdVehicle);
    }

    @PostMapping("/equipment")
    public ResponseEntity<Equipment> addEquipment( @RequestBody Equipment equipment) {
        Equipment createdEquipment = resourceService.createResource(equipment);
        return ResponseEntity
                .created(URI.create("/api/resources/equipment/" + createdEquipment.getId()))
                .body(createdEquipment);
    }

    @PostMapping("/supply")
    public ResponseEntity<Supply> addSupply( @RequestBody Supply supply) {
        Supply createdSupply =  resourceService.createResource(supply);
        return ResponseEntity
                .created(URI.create("/api/resources/supply/" + createdSupply.getId()))
                .body(createdSupply);
    }
}
