package matser2.istic.mmmback.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import matser2.istic.mmmback.DTO.AnomalyDto;
import matser2.istic.mmmback.DTO.CustomerGetDto;
import matser2.istic.mmmback.DTO.CustomerPostDto;
import matser2.istic.mmmback.DTO.PhotoDto;
import matser2.istic.mmmback.DTO.ResourcesDto;
import matser2.istic.mmmback.DTO.WorksiteAllDto;
import matser2.istic.mmmback.DTO.WorksiteGetDto;
import matser2.istic.mmmback.DTO.WorksitePostDto;
import matser2.istic.mmmback.models.Anomaly;
import matser2.istic.mmmback.models.Customer;
import matser2.istic.mmmback.models.Photo;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Worksite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T17:36:17+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class WorksiteMapperImpl implements WorksiteMapper {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public WorksiteAllDto worksiteToWorksiteAllDto(Worksite worksite) {
        if ( worksite == null ) {
            return null;
        }

        WorksiteAllDto worksiteAllDto = new WorksiteAllDto();

        worksiteAllDto.setId( worksite.getId() );
        worksiteAllDto.setDescription( worksite.getDescription() );
        worksiteAllDto.setStartDate( worksite.getStartDate() );
        worksiteAllDto.setLocation( worksite.getLocation() );
        worksiteAllDto.setStatus( worksite.getStatus() );
        worksiteAllDto.setResources( resourcesListToResourcesDtoList( worksite.getResources() ) );
        worksiteAllDto.setCustomer( customerToCustomerGetDto( worksite.getCustomer() ) );
        worksiteAllDto.setAnomalies( anomalyListToAnomalyDtoList( worksite.getAnomalies() ) );
        worksiteAllDto.setTitle( worksite.getTitle() );
        worksiteAllDto.setStatusUpdated( worksite.getStatusUpdated() );
        worksiteAllDto.setDuration( worksite.getDuration() );
        worksiteAllDto.setCreatedAt( worksite.getCreatedAt() );
        worksiteAllDto.setLongitude( worksite.getLongitude() );
        worksiteAllDto.setLatitude( worksite.getLatitude() );
        worksiteAllDto.setPhotos( photoListToPhotoDtoList( worksite.getPhotos() ) );

        return worksiteAllDto;
    }

    @Override
    public Worksite worksiteAllDtoToWorksite(WorksiteAllDto worksiteAllDto) {
        if ( worksiteAllDto == null ) {
            return null;
        }

        Worksite worksite = new Worksite();

        worksite.setId( worksiteAllDto.getId() );
        worksite.setDescription( worksiteAllDto.getDescription() );
        worksite.setStartDate( worksiteAllDto.getStartDate() );
        worksite.setLocation( worksiteAllDto.getLocation() );
        worksite.setCustomer( customerGetDtoToCustomer( worksiteAllDto.getCustomer() ) );
        worksite.setStatus( worksiteAllDto.getStatus() );
        worksite.setResources( resourcesDtoListToResourcesList( worksiteAllDto.getResources() ) );
        worksite.setAnomalies( anomalyDtoListToAnomalyList( worksiteAllDto.getAnomalies() ) );
        worksite.setTitle( worksiteAllDto.getTitle() );
        worksite.setStatusUpdated( worksiteAllDto.getStatusUpdated() );
        worksite.setDuration( worksiteAllDto.getDuration() );
        worksite.setCreatedAt( worksiteAllDto.getCreatedAt() );
        worksite.setLongitude( worksiteAllDto.getLongitude() );
        worksite.setLatitude( worksiteAllDto.getLatitude() );
        worksite.setPhotos( photoDtoListToPhotoList( worksiteAllDto.getPhotos() ) );

        return worksite;
    }

    @Override
    public WorksitePostDto worksiteToWorksitePostDto(Worksite worksite) {
        if ( worksite == null ) {
            return null;
        }

        WorksitePostDto worksitePostDto = new WorksitePostDto();

        worksitePostDto.setId( worksite.getId() );
        worksitePostDto.setDescription( worksite.getDescription() );
        worksitePostDto.setStartDate( worksite.getStartDate() );
        worksitePostDto.setLocation( worksite.getLocation() );
        worksitePostDto.setCustomer( customerToCustomerPostDto( worksite.getCustomer() ) );
        worksitePostDto.setStatus( worksite.getStatus() );
        worksitePostDto.setTitle( worksite.getTitle() );
        worksitePostDto.setStatusUpdated( worksite.getStatusUpdated() );
        worksitePostDto.setDuration( worksite.getDuration() );
        worksitePostDto.setCreatedAt( worksite.getCreatedAt() );
        worksitePostDto.setLongitude( worksite.getLongitude() );
        worksitePostDto.setLatitude( worksite.getLatitude() );
        worksitePostDto.setPhotos( photoListToPhotoDtoList( worksite.getPhotos() ) );

        return worksitePostDto;
    }

    @Override
    public Worksite worksitePostDtoToWorksite(WorksitePostDto worksitePostDto) {
        if ( worksitePostDto == null ) {
            return null;
        }

        Worksite worksite = new Worksite();

        worksite.setId( worksitePostDto.getId() );
        worksite.setDescription( worksitePostDto.getDescription() );
        worksite.setStartDate( worksitePostDto.getStartDate() );
        worksite.setLocation( worksitePostDto.getLocation() );
        worksite.setCustomer( customerPostDtoToCustomer( worksitePostDto.getCustomer() ) );
        worksite.setStatus( worksitePostDto.getStatus() );
        worksite.setTitle( worksitePostDto.getTitle() );
        worksite.setStatusUpdated( worksitePostDto.getStatusUpdated() );
        worksite.setDuration( worksitePostDto.getDuration() );
        worksite.setCreatedAt( worksitePostDto.getCreatedAt() );
        worksite.setLongitude( worksitePostDto.getLongitude() );
        worksite.setLatitude( worksitePostDto.getLatitude() );
        worksite.setPhotos( photoDtoListToPhotoList( worksitePostDto.getPhotos() ) );

        return worksite;
    }

    @Override
    public WorksiteGetDto worksiteToWorksiteGetDto(Worksite worksite) {
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
        worksiteGetDto.setStatus( worksite.getStatus() );
        worksiteGetDto.setTitle( worksite.getTitle() );
        worksiteGetDto.setStatusUpdated( worksite.getStatusUpdated() );
        worksiteGetDto.setDuration( worksite.getDuration() );
        worksiteGetDto.setCreatedAt( worksite.getCreatedAt() );
        worksiteGetDto.setLongitude( worksite.getLongitude() );
        worksiteGetDto.setLatitude( worksite.getLatitude() );

        return worksiteGetDto;
    }

    @Override
    public Worksite worksiteGetDtoToWorksite(WorksiteGetDto worksiteGetDto) {
        if ( worksiteGetDto == null ) {
            return null;
        }

        Worksite worksite = new Worksite();

        worksite.setId( worksiteGetDto.getId() );
        worksite.setDescription( worksiteGetDto.getDescription() );
        worksite.setStartDate( worksiteGetDto.getStartDate() );
        worksite.setLocation( worksiteGetDto.getLocation() );
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

    protected List<ResourcesDto> resourcesListToResourcesDtoList(List<Resources> list) {
        if ( list == null ) {
            return null;
        }

        List<ResourcesDto> list1 = new ArrayList<ResourcesDto>( list.size() );
        for ( Resources resources : list ) {
            list1.add( resourcesMapper.resourcesToResourcesDto( resources ) );
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

    protected List<Resources> resourcesDtoListToResourcesList(List<ResourcesDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Resources> list1 = new ArrayList<Resources>( list.size() );
        for ( ResourcesDto resourcesDto : list ) {
            list1.add( resourcesMapper.resourcesDtoToResources( resourcesDto ) );
        }

        return list1;
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

    protected CustomerPostDto customerToCustomerPostDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerPostDto customerPostDto = new CustomerPostDto();

        customerPostDto.setId( customer.getId() );
        customerPostDto.setName( customer.getName() );
        customerPostDto.setEmail( customer.getEmail() );
        customerPostDto.setPhoneNumber( String.valueOf( customer.getPhoneNumber() ) );

        return customerPostDto;
    }

    protected Customer customerPostDtoToCustomer(CustomerPostDto customerPostDto) {
        if ( customerPostDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        if ( customerPostDto.getPhoneNumber() != null ) {
            customer.setPhoneNumber( Integer.parseInt( customerPostDto.getPhoneNumber() ) );
        }
        customer.setId( customerPostDto.getId() );
        customer.setName( customerPostDto.getName() );
        customer.setEmail( customerPostDto.getEmail() );

        return customer;
    }
}
