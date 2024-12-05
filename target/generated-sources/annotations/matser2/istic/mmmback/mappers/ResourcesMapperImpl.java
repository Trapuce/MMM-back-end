package matser2.istic.mmmback.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import matser2.istic.mmmback.DTO.AnomalyDto;
import matser2.istic.mmmback.DTO.AvailabilityDto;
import matser2.istic.mmmback.DTO.CustomerGetDto;
import matser2.istic.mmmback.DTO.EmployeeDto;
import matser2.istic.mmmback.DTO.EquipmentDto;
import matser2.istic.mmmback.DTO.PhotoDto;
import matser2.istic.mmmback.DTO.VehicleDto;
import matser2.istic.mmmback.DTO.WorksiteGetDto;
import matser2.istic.mmmback.models.Anomaly;
import matser2.istic.mmmback.models.Availability;
import matser2.istic.mmmback.models.Customer;
import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Equipment;
import matser2.istic.mmmback.models.Photo;
import matser2.istic.mmmback.models.Vehicle;
import matser2.istic.mmmback.models.Worksite;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-05T13:22:29+0100",
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
        equipmentDto.setWorksites( worksiteListToWorksiteGetDtoList( equipment.getWorksites() ) );
        equipmentDto.setAvailabilities( availabilityListToAvailabilityDtoList( equipment.getAvailabilities() ) );
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
        equipment.setWorksites( worksiteGetDtoListToWorksiteList( equipmentDto.getWorksites() ) );
        equipment.setAvailabilities( availabilityDtoListToAvailabilityList( equipmentDto.getAvailabilities() ) );
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
        vehicleDto.setWorksites( worksiteListToWorksiteGetDtoList( vehicle.getWorksites() ) );
        vehicleDto.setAvailabilities( availabilityListToAvailabilityDtoList( vehicle.getAvailabilities() ) );
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
        vehicle.setWorksites( worksiteGetDtoListToWorksiteList( vehicleDto.getWorksites() ) );
        vehicle.setAvailabilities( availabilityDtoListToAvailabilityList( vehicleDto.getAvailabilities() ) );
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
        employeeDto.setWorksites( worksiteListToWorksiteGetDtoList( employee.getWorksites() ) );
        employeeDto.setAvailabilities( availabilityListToAvailabilityDtoList( employee.getAvailabilities() ) );
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
        employee.setWorksites( worksiteGetDtoListToWorksiteList( employeeDto.getWorksites() ) );
        employee.setAvailabilities( availabilityDtoListToAvailabilityList( employeeDto.getAvailabilities() ) );
        employee.setFirstName( employeeDto.getFirstName() );
        employee.setLastName( employeeDto.getLastName() );
        employee.setEmail( employeeDto.getEmail() );
        employee.setPassword( employeeDto.getPassword() );
        employee.setRole( employeeDto.getRole() );

        return employee;
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
        anomalyDto.setTitle( anomaly.getTitle() );
        anomalyDto.setSeverityLevel( anomaly.getSeverityLevel() );
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

    protected CustomerGetDto customerToCustomerGetDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerGetDto customerGetDto = new CustomerGetDto();

        customerGetDto.setId( customer.getId() );
        customerGetDto.setName( customer.getName() );
        customerGetDto.setPhoneNumber( String.valueOf( customer.getPhoneNumber() ) );
        customerGetDto.setEmail( customer.getEmail() );

        return customerGetDto;
    }

    protected WorksiteGetDto worksiteToWorksiteGetDto(Worksite worksite) {
        if ( worksite == null ) {
            return null;
        }

        WorksiteGetDto worksiteGetDto = new WorksiteGetDto();

        worksiteGetDto.setPhotos( photoListToPhotoDtoList( worksite.getPhotos() ) );
        worksiteGetDto.setAnomalies( anomalyListToAnomalyDtoList( worksite.getAnomalies() ) );
        worksiteGetDto.setDescription( worksite.getDescription() );
        worksiteGetDto.setStartDate( worksite.getStartDate() );
        worksiteGetDto.setId( worksite.getId() );
        worksiteGetDto.setLocation( worksite.getLocation() );
        worksiteGetDto.setCustomer( customerToCustomerGetDto( worksite.getCustomer() ) );
        worksiteGetDto.setStatus( worksite.getStatus() );
        worksiteGetDto.setTitle( worksite.getTitle() );
        worksiteGetDto.setStatusUpdated( worksite.getStatusUpdated() );
        worksiteGetDto.setDuration( worksite.getDuration() );
        worksiteGetDto.setCreatedAt( worksite.getCreatedAt() );
        worksiteGetDto.setLongitude( worksite.getLongitude() );
        worksiteGetDto.setLatitude( worksite.getLatitude() );

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

    protected AvailabilityDto availabilityToAvailabilityDto(Availability availability) {
        if ( availability == null ) {
            return null;
        }

        AvailabilityDto availabilityDto = new AvailabilityDto();

        availabilityDto.setId( availability.getId() );
        availabilityDto.setStartTime( availability.getStartTime() );
        availabilityDto.setEndTime( availability.getEndTime() );

        return availabilityDto;
    }

    protected List<AvailabilityDto> availabilityListToAvailabilityDtoList(List<Availability> list) {
        if ( list == null ) {
            return null;
        }

        List<AvailabilityDto> list1 = new ArrayList<AvailabilityDto>( list.size() );
        for ( Availability availability : list ) {
            list1.add( availabilityToAvailabilityDto( availability ) );
        }

        return list1;
    }

    protected Customer customerGetDtoToCustomer(CustomerGetDto customerGetDto) {
        if ( customerGetDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        if ( customerGetDto.getPhoneNumber() != null ) {
            customer.setPhoneNumber( Integer.parseInt( customerGetDto.getPhoneNumber() ) );
        }
        customer.setId( customerGetDto.getId() );
        customer.setName( customerGetDto.getName() );
        customer.setEmail( customerGetDto.getEmail() );

        return customer;
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
        anomaly.setTitle( anomalyDto.getTitle() );
        anomaly.setSeverityLevel( anomalyDto.getSeverityLevel() );
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
        worksite.setLocation( worksiteGetDto.getLocation() );
        worksite.setCustomer( customerGetDtoToCustomer( worksiteGetDto.getCustomer() ) );
        worksite.setStatus( worksiteGetDto.getStatus() );
        worksite.setAnomalies( anomalyDtoListToAnomalyList( worksiteGetDto.getAnomalies() ) );
        worksite.setTitle( worksiteGetDto.getTitle() );
        worksite.setStatusUpdated( worksiteGetDto.getStatusUpdated() );
        worksite.setDuration( worksiteGetDto.getDuration() );
        worksite.setCreatedAt( worksiteGetDto.getCreatedAt() );
        worksite.setLongitude( worksiteGetDto.getLongitude() );
        worksite.setLatitude( worksiteGetDto.getLatitude() );
        worksite.setPhotos( photoDtoListToPhotoList( worksiteGetDto.getPhotos() ) );

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

    protected Availability availabilityDtoToAvailability(AvailabilityDto availabilityDto) {
        if ( availabilityDto == null ) {
            return null;
        }

        Availability availability = new Availability();

        availability.setId( availabilityDto.getId() );
        availability.setStartTime( availabilityDto.getStartTime() );
        availability.setEndTime( availabilityDto.getEndTime() );

        return availability;
    }

    protected List<Availability> availabilityDtoListToAvailabilityList(List<AvailabilityDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Availability> list1 = new ArrayList<Availability>( list.size() );
        for ( AvailabilityDto availabilityDto : list ) {
            list1.add( availabilityDtoToAvailability( availabilityDto ) );
        }

        return list1;
    }
}
