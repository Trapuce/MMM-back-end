package matser2.istic.mmmback.mappers;

import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ResourcesFactory.class})
public interface ResourcesSimpleMapper {
    EquipmentSummaryDto equipmentToEquipmentSummaryDto(Equipment equipment);
    VehicleSummaryDto vehicleToVehicleSummaryDto(Vehicle vehicle);
    EmployeeSummaryDto employeeToEmployeeSummaryDto(Employee employee);

    default ResourcesSimpleDto resourcesToResourcesSimpleDto(Resources resources) {
        if (resources instanceof Employee) {
            return employeeToEmployeeSummaryDto((Employee) resources);
        } else if (resources instanceof Equipment) {
            return equipmentToEquipmentSummaryDto((Equipment) resources);
        } else if (resources instanceof Vehicle) {
            return vehicleToVehicleSummaryDto((Vehicle) resources);
        } else {
            return null;
        }
    }
}