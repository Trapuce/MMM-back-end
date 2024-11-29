package matser2.istic.mmmback.controllers;


import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Vehicle;
import matser2.istic.mmmback.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Employee>> getAvailableEmployees(
            @RequestParam(required = true) Date startDate,
            @RequestParam(required = true) Date endDate) {

        if (startDate == null || endDate == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (startDate.after(endDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        try {
            List<Employee> availableEmployees = availabilityService.getAvailableEmployees(startDate, endDate);
            if (availableEmployees.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(availableEmployees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAvailableVehicles(
            @RequestParam(required = true) Date startDate,
            @RequestParam(required = true) Date endDate) {

        if (startDate == null || endDate == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (startDate.after(endDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        try {
            List<Vehicle> availableVehicles = availabilityService.getAvailableVehicles(startDate, endDate);
            if (availableVehicles.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(availableVehicles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Get available equipment within a date range
    @GetMapping("/equipments")
    public ResponseEntity<List<Equipment>> getAvailableEquipments(
            @RequestParam(required = true) Date startDate,
            @RequestParam(required = true) Date endDate) {

        // Validate the dates
        if (startDate == null || endDate == null) {
            return ResponseEntity.badRequest().body(null);  // 400 Bad Request for missing date parameters
        }

        if (startDate.after(endDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);  // 400 Bad Request for invalid date range
        }

        try {
            List<Equipment> availableEquipments = availabilityService.getAvailableEquipments(startDate, endDate);
            if (availableEquipments.isEmpty()) {
                return ResponseEntity.noContent().build();  // 204 No Content if no equipment found
            }
            return ResponseEntity.ok(availableEquipments);  // 200 OK with list of equipment
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // 500 Internal Server Error if any exception occurs
        }
    }
}

