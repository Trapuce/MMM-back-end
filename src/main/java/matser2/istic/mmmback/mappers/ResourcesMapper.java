package matser2.istic.mmmback.mappers;


import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ResourcesFactory.class})
public interface ResourcesMapper {

    EquipmentDto equipmentToEquipmentDto(Equipment equipment);
    Equipment equipmentDtoToEquipment(EquipmentDto equipmentDto);

    VehicleDto vehicleToVehicleDto(Vehicle vehicle);
    Vehicle vehicleDtoToVehicle(VehicleDto vehicleDto);

    EmployeeDto employeeToEmployeeDto(Employee employee);
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    default Resources resourcesDtoToResources(ResourcesDto resourcesDto) {
        if (resourcesDto instanceof EmployeeDto) {
            return employeeDtoToEmployee((EmployeeDto) resourcesDto);
        } else if (resourcesDto instanceof EquipmentDto) {
            return equipmentDtoToEquipment((EquipmentDto) resourcesDto);
        } else if (resourcesDto instanceof VehicleDto) {
            return vehicleDtoToVehicle((VehicleDto) resourcesDto);
        } else {
            return null;
        }
    }
    default ResourcesDto resourcesToResourcesDto(Resources resources) {
        if (resources instanceof Employee) {
            return employeeToEmployeeDto((Employee) resources);
        } else if (resources instanceof Equipment) {
            return equipmentToEquipmentDto((Equipment) resources);
        } else if (resources instanceof Vehicle) {
            return vehicleToVehicleDto((Vehicle) resources);
        } else {
            return null;
        }
    }
}
