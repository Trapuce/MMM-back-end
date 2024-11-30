package matser2.istic.mmmback.mappers;

import javax.annotation.processing.Generated;
import matser2.istic.mmmback.DTO.CompanyDto;
import matser2.istic.mmmback.models.Company;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T22:26:05+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDto companyToCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId( company.getId() );
        companyDto.setName( company.getName() );
        companyDto.setRegistrationNumber( company.getRegistrationNumber() );
        companyDto.setAddress( company.getAddress() );

        return companyDto;
    }

    @Override
    public Company companyDtoToCompany(CompanyDto companyDto) {
        if ( companyDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( companyDto.getId() );
        company.setName( companyDto.getName() );
        company.setRegistrationNumber( companyDto.getRegistrationNumber() );
        company.setAddress( companyDto.getAddress() );

        return company;
    }
}
