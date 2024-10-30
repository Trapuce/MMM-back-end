package matser2.istic.mmmback.mappers;

import matser2.istic.mmmback.DTO.*;
import matser2.istic.mmmback.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.SubclassExhaustiveStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface ResourcesMapper {

    EquipmentDto equipmentToEquipmentDto(Equipment equipment);
    Equipment equipmentDtoToEquipment(EquipmentDto equipmentDto);

    VehicleDto vehicleToVehicleDto(Vehicle vehicle);
    Vehicle vehicleDtoToVehicle(VehicleDto vehicleDto);

    EmployeeAllDto employeeToEmployeeDto(Employee employee);
    Employee employeeDtoToEmployee(EmployeeAllDto employeeDto);

  /*  default ResourcesDto resourcesToResourcesDto(Resources resources) {
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

    default Resources resourcesDtoToResources(ResourcesDto resourcesDto) {
        if (resourcesDto instanceof EmployeeAllDto) {
            return employeeDtoToEmployee((EmployeeAllDto) resourcesDto);
        } else if (resourcesDto instanceof EquipmentDto) {
            return equipmentDtoToEquipment((EquipmentDto) resourcesDto);
        } else if (resourcesDto instanceof VehicleDto) {
            return vehicleDtoToVehicle((VehicleDto) resourcesDto);
        } else {
            return null;
        }
    }
    @ObjectFactory
    default Resources createResources(ResourcesDto resourcesDto) {
        if (resourcesDto instanceof EmployeeAllDto) {
            return new Employee();
        } else if (resourcesDto instanceof EquipmentDto) {
            return new Equipment();
        } else if (resourcesDto instanceof VehicleDto) {
            return new Vehicle();
        }
        throw new IllegalArgumentException("Unknown ResourcesDto type: " + resourcesDto.getClass());
    }

    @ObjectFactory
    default ResourcesDto createResourcesDto(Resources resources) {
        if (resources instanceof Employee) {
            return new EmployeeAllDto();
        } else if (resources instanceof Equipment) {
            return new EquipmentDto();
        } else if (resources instanceof Vehicle) {
            return new VehicleDto();
        }
        throw new IllegalArgumentException("Unknown Resources type: " + resources.getClass());
    }*/
    List<WorksiteAllDto> worksiteToWorksiteAllDto(List<Worksite> worksite);
}
