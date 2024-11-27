package matser2.istic.mmmback.controllers;


import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Vehicle;
import matser2.istic.mmmback.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/availability")
public class AvailabilityController {


    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/employees")
    public List<Employee> getAvailableEmployees(@RequestParam Date startDate, @RequestParam Date endDate) {
        return availabilityService.getAvailableEmployees(startDate, endDate);
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAvailableVehicles(@RequestParam Date startDate, @RequestParam Date endDate) {
        return availabilityService.getAvailableVehicles(startDate, endDate);
    }

    @GetMapping("/equipments")
    public List<Equipment> getAvailableEquipments(@RequestParam Date startDate, @RequestParam Date endDate) {
        return availabilityService.getAvailableEquipments(startDate, endDate);
    }
}
