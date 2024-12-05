package matser2.istic.mmmback.mappers;

import javax.annotation.processing.Generated;
import matser2.istic.mmmback.DTO.EmployeeSummaryDto;
import matser2.istic.mmmback.DTO.EquipmentSummaryDto;
import matser2.istic.mmmback.DTO.VehicleSummaryDto;
import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Vehicle;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-05T12:34:57+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ResourcesSimpleMapperImpl implements ResourcesSimpleMapper {

    @Override
    public EquipmentSummaryDto equipmentToEquipmentSummaryDto(Equipment equipment) {
        if ( equipment == null ) {
            return null;
        }

        EquipmentSummaryDto equipmentSummaryDto = new EquipmentSummaryDto();

        equipmentSummaryDto.setId( equipment.getId() );
        equipmentSummaryDto.setName( equipment.getName() );
        equipmentSummaryDto.setType( equipment.getType() );
        equipmentSummaryDto.setSerialNumber( equipment.getSerialNumber() );

        return equipmentSummaryDto;
    }

    @Override
    public VehicleSummaryDto vehicleToVehicleSummaryDto(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleSummaryDto vehicleSummaryDto = new VehicleSummaryDto();

        vehicleSummaryDto.setId( vehicle.getId() );
        vehicleSummaryDto.setName( vehicle.getName() );
        vehicleSummaryDto.setLicensePlate( vehicle.getLicensePlate() );
        vehicleSummaryDto.setModel( vehicle.getModel() );

        return vehicleSummaryDto;
    }

    @Override
    public EmployeeSummaryDto employeeToEmployeeSummaryDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeSummaryDto employeeSummaryDto = new EmployeeSummaryDto();

        employeeSummaryDto.setId( employee.getId() );
        employeeSummaryDto.setName( employee.getName() );
        employeeSummaryDto.setFirstName( employee.getFirstName() );
        employeeSummaryDto.setLastName( employee.getLastName() );
        employeeSummaryDto.setEmail( employee.getEmail() );
        employeeSummaryDto.setPassword( employee.getPassword() );
        employeeSummaryDto.setRole( employee.getRole() );

        return employeeSummaryDto;
    }
}
