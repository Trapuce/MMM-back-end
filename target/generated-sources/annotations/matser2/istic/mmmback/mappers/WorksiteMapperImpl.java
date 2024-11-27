package matser2.istic.mmmback.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import matser2.istic.mmmback.DTO.AnomalyDto;
import matser2.istic.mmmback.DTO.CompanyGetDto;
import matser2.istic.mmmback.DTO.CompanyPostDto;
import matser2.istic.mmmback.DTO.CustomerGetDto;
import matser2.istic.mmmback.DTO.CustomerPostDto;
import matser2.istic.mmmback.DTO.PhotoDto;
import matser2.istic.mmmback.DTO.ResourcesDto;
import matser2.istic.mmmback.DTO.WorksiteAllDto;
import matser2.istic.mmmback.DTO.WorksiteGetDto;
import matser2.istic.mmmback.DTO.WorksitePostDto;
import matser2.istic.mmmback.models.Anomaly;
import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.models.Customer;
import matser2.istic.mmmback.models.Photo;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Worksite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T20:27:53+0100",
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
        worksiteAllDto.setDurationInHalfDays( worksite.getDurationInHalfDays() );
        worksiteAllDto.setStartDate( worksite.getStartDate() );
        worksiteAllDto.setLocation( worksite.getLocation() );
        worksiteAllDto.setStatus( worksite.getStatus() );
        worksiteAllDto.setResources( resourcesListToResourcesDtoList( worksite.getResources() ) );
        worksiteAllDto.setCustomer( customerToCustomerGetDto( worksite.getCustomer() ) );
        worksiteAllDto.setCompany( companyToCompanyGetDto( worksite.getCompany() ) );
        worksiteAllDto.setAnomalies( anomalyListToAnomalyDtoList( worksite.getAnomalies() ) );

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
        worksite.setDurationInHalfDays( worksiteAllDto.getDurationInHalfDays() );
        worksite.setLocation( worksiteAllDto.getLocation() );
        worksite.setCustomer( customerGetDtoToCustomer( worksiteAllDto.getCustomer() ) );
        worksite.setStatus( worksiteAllDto.getStatus() );
        worksite.setResources( resourcesDtoListToResourcesList( worksiteAllDto.getResources() ) );
        worksite.setCompany( companyGetDtoToCompany( worksiteAllDto.getCompany() ) );
        worksite.setAnomalies( anomalyDtoListToAnomalyList( worksiteAllDto.getAnomalies() ) );

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
        worksitePostDto.setDurationInHalfDays( worksite.getDurationInHalfDays() );
        worksitePostDto.setCustomer( customerToCustomerPostDto( worksite.getCustomer() ) );
        worksitePostDto.setStatus( worksite.getStatus() );
        worksitePostDto.setCompany( companyToCompanyPostDto( worksite.getCompany() ) );

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
        worksite.setDurationInHalfDays( worksitePostDto.getDurationInHalfDays() );
        worksite.setLocation( worksitePostDto.getLocation() );
        worksite.setCustomer( customerPostDtoToCustomer( worksitePostDto.getCustomer() ) );
        worksite.setStatus( worksitePostDto.getStatus() );
        worksite.setCompany( companyPostDtoToCompany( worksitePostDto.getCompany() ) );

        return worksite;
    }

    @Override
    public WorksiteGetDto worksiteToWorksiteGetDto(Worksite worksite) {
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

    @Override
    public Worksite worksiteGetDtoToWorksite(WorksiteGetDto worksiteGetDto) {
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

        return customerGetDto;
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

    protected Customer customerGetDtoToCustomer(CustomerGetDto customerGetDto) {
        if ( customerGetDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerGetDto.getId() );
        customer.setName( customerGetDto.getName() );

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

    protected CustomerPostDto customerToCustomerPostDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerPostDto customerPostDto = new CustomerPostDto();

        customerPostDto.setId( customer.getId() );
        customerPostDto.setName( customer.getName() );
        customerPostDto.setEmail( customer.getEmail() );

        return customerPostDto;
    }

    protected CompanyPostDto companyToCompanyPostDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyPostDto companyPostDto = new CompanyPostDto();

        companyPostDto.setId( company.getId() );
        companyPostDto.setName( company.getName() );
        companyPostDto.setRegistrationNumber( company.getRegistrationNumber() );
        companyPostDto.setAddress( company.getAddress() );

        return companyPostDto;
    }

    protected Customer customerPostDtoToCustomer(CustomerPostDto customerPostDto) {
        if ( customerPostDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerPostDto.getId() );
        customer.setName( customerPostDto.getName() );
        customer.setEmail( customerPostDto.getEmail() );

        return customer;
    }

    protected Company companyPostDtoToCompany(CompanyPostDto companyPostDto) {
        if ( companyPostDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( companyPostDto.getId() );
        company.setName( companyPostDto.getName() );
        company.setRegistrationNumber( companyPostDto.getRegistrationNumber() );
        company.setAddress( companyPostDto.getAddress() );

        return company;
    }
}
