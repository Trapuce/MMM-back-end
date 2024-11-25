package matser2.istic.mmmback.DTO;

import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Vehicle;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;

public class ResourcesFactory {

    @ObjectFactory
    public ResourcesDto createResourcesDto(Resources resource) {
        if (resource instanceof Vehicle) {
            VehicleDto vehicleDto = new VehicleDto();
            return vehicleDto;
        } else if (resource instanceof Equipment) {
            EquipmentDto equipmentDto = new EquipmentDto();
            return equipmentDto;
        } else if (resource instanceof Employee) {
            EmployeeDto employeeDto = new EmployeeDto();
            return employeeDto;
        }
        return null;
    }
}
