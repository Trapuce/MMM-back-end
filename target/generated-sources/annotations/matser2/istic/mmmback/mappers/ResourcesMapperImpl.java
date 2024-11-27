package matser2.istic.mmmback.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import matser2.istic.mmmback.DTO.AnomalyDto;
import matser2.istic.mmmback.DTO.CompanyGetDto;
import matser2.istic.mmmback.DTO.EmployeeDto;
import matser2.istic.mmmback.DTO.EquipmentDto;
import matser2.istic.mmmback.DTO.PhotoDto;
import matser2.istic.mmmback.DTO.VehicleDto;
import matser2.istic.mmmback.DTO.WorksiteGetDto;
import matser2.istic.mmmback.models.Anomaly;
import matser2.istic.mmmback.models.Availability;
import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Photo;
import matser2.istic.mmmback.models.Vehicle;
import matser2.istic.mmmback.models.Worksite;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T20:27:54+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ResourcesMapperImpl implements ResourcesMapper {

    @Override
    public EquipmentDto equipmentToEquipmentDto(Equipment equipment) {
        if ( equipment == null ) {
            return null;
        }

        EquipmentDto equipmentDto = new EquipmentDto();

        equipmentDto.setName( equipment.getName() );
        equipmentDto.setId( equipment.getId() );
        equipmentDto.setCompany( companyToCompanyGetDto( equipment.getCompany() ) );
        equipmentDto.setWorksites( worksiteListToWorksiteGetDtoList( equipment.getWorksites() ) );
        List<Availability> list1 = equipment.getAvailabilities();
        if ( list1 != null ) {
            equipmentDto.setAvailabilities( new ArrayList<Availability>( list1 ) );
        }
        equipmentDto.setType( equipment.getType() );
        equipmentDto.setSerialNumber( equipment.getSerialNumber() );

        return equipmentDto;
    }

    @Override
    public Equipment equipmentDtoToEquipment(EquipmentDto equipmentDto) {
        if ( equipmentDto == null ) {
            return null;
        }

        Equipment equipment = new Equipment();

        equipment.setId( equipmentDto.getId() );
        equipment.setName( equipmentDto.getName() );
        equipment.setCompany( companyGetDtoToCompany( equipmentDto.getCompany() ) );
        equipment.setWorksites( worksiteGetDtoListToWorksiteList( equipmentDto.getWorksites() ) );
        List<Availability> list1 = equipmentDto.getAvailabilities();
        if ( list1 != null ) {
            equipment.setAvailabilities( new ArrayList<Availability>( list1 ) );
        }
        equipment.setType( equipmentDto.getType() );
        equipment.setSerialNumber( equipmentDto.getSerialNumber() );

        return equipment;
    }

    @Override
    public VehicleDto vehicleToVehicleDto(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleDto vehicleDto = new VehicleDto();

        vehicleDto.setName( vehicle.getName() );
        vehicleDto.setId( vehicle.getId() );
        vehicleDto.setCompany( companyToCompanyGetDto( vehicle.getCompany() ) );
        vehicleDto.setWorksites( worksiteListToWorksiteGetDtoList( vehicle.getWorksites() ) );
        List<Availability> list1 = vehicle.getAvailabilities();
        if ( list1 != null ) {
            vehicleDto.setAvailabilities( new ArrayList<Availability>( list1 ) );
        }
        vehicleDto.setLicensePlate( vehicle.getLicensePlate() );
        vehicleDto.setModel( vehicle.getModel() );

        return vehicleDto;
    }

    @Override
    public Vehicle vehicleDtoToVehicle(VehicleDto vehicleDto) {
        if ( vehicleDto == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( vehicleDto.getId() );
        vehicle.setName( vehicleDto.getName() );
        vehicle.setCompany( companyGetDtoToCompany( vehicleDto.getCompany() ) );
        vehicle.setWorksites( worksiteGetDtoListToWorksiteList( vehicleDto.getWorksites() ) );
        List<Availability> list1 = vehicleDto.getAvailabilities();
        if ( list1 != null ) {
            vehicle.setAvailabilities( new ArrayList<Availability>( list1 ) );
        }
        vehicle.setLicensePlate( vehicleDto.getLicensePlate() );
        vehicle.setModel( vehicleDto.getModel() );

        return vehicle;
    }

    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setName( employee.getName() );
        employeeDto.setId( employee.getId() );
        employeeDto.setCompany( companyToCompanyGetDto( employee.getCompany() ) );
        employeeDto.setWorksites( worksiteListToWorksiteGetDtoList( employee.getWorksites() ) );
        List<Availability> list1 = employee.getAvailabilities();
        if ( list1 != null ) {
            employeeDto.setAvailabilities( new ArrayList<Availability>( list1 ) );
        }
        employeeDto.setFirstName( employee.getFirstName() );
        employeeDto.setLastName( employee.getLastName() );
        employeeDto.setEmail( employee.getEmail() );
        employeeDto.setPassword( employee.getPassword() );
        employeeDto.setRole( employee.getRole() );

        return employeeDto;
    }

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.getId() );
        employee.setName( employeeDto.getName() );
        employee.setCompany( companyGetDtoToCompany( employeeDto.getCompany() ) );
        employee.setWorksites( worksiteGetDtoListToWorksiteList( employeeDto.getWorksites() ) );
        List<Availability> list1 = employeeDto.getAvailabilities();
        if ( list1 != null ) {
            employee.setAvailabilities( new ArrayList<Availability>( list1 ) );
        }
        employee.setFirstName( employeeDto.getFirstName() );
        employee.setLastName( employeeDto.getLastName() );
        employee.setEmail( employeeDto.getEmail() );
        employee.setPassword( employeeDto.getPassword() );
        employee.setRole( employeeDto.getRole() );

        return employee;
    }

    protected CompanyGetDto companyToCompanyGetDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyGetDto companyGetDto = new CompanyGetDto();

        companyGetDto.setId( company.getId() );
        companyGetDto.setName( company.getName() );
        companyGetDto.setRegistrationNumber( company.getRegistrationNumber() );
        companyGetDto.setAddress( company.getAddress() );

        return companyGetDto;
    }

    protected PhotoDto photoToPhotoDto(Photo photo) {
        if ( photo == null ) {
            return null;
        }

        PhotoDto photoDto = new PhotoDto();

        photoDto.setId( photo.getId() );
        photoDto.setFilePath( photo.getFilePath() );

        return photoDto;
    }

    protected List<PhotoDto> photoListToPhotoDtoList(List<Photo> list) {
        if ( list == null ) {
            return null;
        }

        List<PhotoDto> list1 = new ArrayList<PhotoDto>( list.size() );
        for ( Photo photo : list ) {
            list1.add( photoToPhotoDto( photo ) );
        }

        return list1;
    }

    protected AnomalyDto anomalyToAnomalyDto(Anomaly anomaly) {
        if ( anomaly == null ) {
            return null;
        }

        AnomalyDto anomalyDto = new AnomalyDto();

        anomalyDto.setId( anomaly.getId() );
        anomalyDto.setDescription( anomaly.getDescription() );
        anomalyDto.setPhotos( photoListToPhotoDtoList( anomaly.getPhotos() ) );

        return anomalyDto;
    }

    protected List<AnomalyDto> anomalyListToAnomalyDtoList(List<Anomaly> list) {
        if ( list == null ) {
            return null;
        }

        List<AnomalyDto> list1 = new ArrayList<AnomalyDto>( list.size() );
        for ( Anomaly anomaly : list ) {
            list1.add( anomalyToAnomalyDto( anomaly ) );
        }

        return list1;
    }

    protected WorksiteGetDto worksiteToWorksiteGetDto(Worksite worksite) {
        if ( worksite == null ) {
            return null;
        }

        WorksiteGetDto worksiteGetDto = new WorksiteGetDto();

        worksiteGetDto.setAnomalies( anomalyListToAnomalyDtoList( worksite.getAnomalies() ) );
        worksiteGetDto.setDescription( worksite.getDescription() );
        worksiteGetDto.setStartDate( worksite.getStartDate() );
        worksiteGetDto.setId( worksite.getId() );
        worksiteGetDto.setDurationInHalfDays( worksite.getDurationInHalfDays() );
        worksiteGetDto.setLocation( worksite.getLocation() );
        worksiteGetDto.setStatus( worksite.getStatus() );

        return worksiteGetDto;
    }

    protected List<WorksiteGetDto> worksiteListToWorksiteGetDtoList(List<Worksite> list) {
        if ( list == null ) {
            return null;
        }

        List<WorksiteGetDto> list1 = new ArrayList<WorksiteGetDto>( list.size() );
        for ( Worksite worksite : list ) {
            list1.add( worksiteToWorksiteGetDto( worksite ) );
        }

        return list1;
    }

    protected Company companyGetDtoToCompany(CompanyGetDto companyGetDto) {
        if ( companyGetDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( companyGetDto.getId() );
        company.setName( companyGetDto.getName() );
        company.setRegistrationNumber( companyGetDto.getRegistrationNumber() );
        company.setAddress( companyGetDto.getAddress() );

        return company;
    }

    protected Photo photoDtoToPhoto(PhotoDto photoDto) {
        if ( photoDto == null ) {
            return null;
        }

        Photo photo = new Photo();

        photo.setId( photoDto.getId() );
        photo.setFilePath( photoDto.getFilePath() );

        return photo;
    }

    protected List<Photo> photoDtoListToPhotoList(List<PhotoDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Photo> list1 = new ArrayList<Photo>( list.size() );
        for ( PhotoDto photoDto : list ) {
            list1.add( photoDtoToPhoto( photoDto ) );
        }

        return list1;
    }

    protected Anomaly anomalyDtoToAnomaly(AnomalyDto anomalyDto) {
        if ( anomalyDto == null ) {
            return null;
        }

        Anomaly anomaly = new Anomaly();

        anomaly.setId( anomalyDto.getId() );
        anomaly.setDescription( anomalyDto.getDescription() );
        anomaly.setPhotos( photoDtoListToPhotoList( anomalyDto.getPhotos() ) );

        return anomaly;
    }

    protected List<Anomaly> anomalyDtoListToAnomalyList(List<AnomalyDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Anomaly> list1 = new ArrayList<Anomaly>( list.size() );
        for ( AnomalyDto anomalyDto : list ) {
            list1.add( anomalyDtoToAnomaly( anomalyDto ) );
        }

        return list1;
    }

    protected Worksite worksiteGetDtoToWorksite(WorksiteGetDto worksiteGetDto) {
        if ( worksiteGetDto == null ) {
            return null;
        }

        Worksite worksite = new Worksite();

        worksite.setId( worksiteGetDto.getId() );
        worksite.setDescription( worksiteGetDto.getDescription() );
        worksite.setStartDate( worksiteGetDto.getStartDate() );
        worksite.setDurationInHalfDays( worksiteGetDto.getDurationInHalfDays() );
        worksite.setLocation( worksiteGetDto.getLocation() );
        worksite.setStatus( worksiteGetDto.getStatus() );
        worksite.setAnomalies( anomalyDtoListToAnomalyList( worksiteGetDto.getAnomalies() ) );

        return worksite;
    }

    protected List<Worksite> worksiteGetDtoListToWorksiteList(List<WorksiteGetDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Worksite> list1 = new ArrayList<Worksite>( list.size() );
        for ( WorksiteGetDto worksiteGetDto : list ) {
            list1.add( worksiteGetDtoToWorksite( worksiteGetDto ) );
        }

        return list1;
    }
}
