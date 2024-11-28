package matser2.istic.mmmback.mappers;


import matser2.istic.mmmback.DTO.CompanyDto;
import matser2.istic.mmmback.models.Company;
import org.mapstruct.*;


@Mapper(
        componentModel = "spring" , uses = {ResourcesMapper.class}
)
public interface CompanyMapper {


    CompanyDto companyToCompanyDto(Company company);

    Company companyDtoToCompany(CompanyDto companyDto);




}