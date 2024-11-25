package matser2.istic.mmmback.service;

import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Vehicle;
import matser2.istic.mmmback.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AvailabilityService {

    @Autowired
    private  AvailabilityRepository availabilityRepository;



    public List<Resources> getAvailableResources(Date startDate, int durationInHalfDays) {
        Date endDate = calculateEndDate(startDate, durationInHalfDays);
        return availabilityRepository.findAvailableResources(startDate, endDate);
    }

    private Date calculateEndDate(Date startDate, int durationInHalfDays) {
        long milliseconds = durationInHalfDays * 4 * 60 * 60 * 1000L;
        return new Date(startDate.getTime() + milliseconds);
    }
    public List<Resources> getAvailableResources(Date startDate, Date endDate) {
        return availabilityRepository.findAvailableResources(startDate, endDate);
    }

    public boolean isResourceAvailable(Resources resource, Date startDate, Date endDate) {
        return availabilityRepository.findConflictingAvailabilities(resource, startDate, endDate).isEmpty();
    }

   public List<Employee> getAvailableEmployees(Date startDate, Date endDate) {
        return availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Employee)
                .map(resource -> (Employee) resource)
                .toList();
    }

    public List<Vehicle> getAvailableVehicles(Date startDate, Date endDate) {
        return availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Vehicle)
                .map(resource -> (Vehicle) resource)
                .toList();
    }

    public List<Equipment> getAvailableEquipments(Date startDate, Date endDate) {
        return availabilityRepository.findAvailableResources(startDate, endDate).stream()
                .filter(resource -> resource instanceof Equipment)
                .map(resource -> (Equipment) resource)
                .toList();
    }
}