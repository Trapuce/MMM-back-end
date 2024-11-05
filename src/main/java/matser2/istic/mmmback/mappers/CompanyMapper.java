package matser2.istic.mmmback.mappers;


import matser2.istic.mmmback.DTO.CompanyAllDto;
import matser2.istic.mmmback.DTO.CompanyGetDto;
import matser2.istic.mmmback.DTO.CompanyPostDto;
import matser2.istic.mmmback.models.Company;
import org.mapstruct.*;


@Mapper(
        componentModel = "spring"
)
public interface CompanyMapper {

    CompanyAllDto companyToCompanyAllDto(Company company);

    Company companyAllDtoToCompany(CompanyAllDto companyAllDto);

    CompanyPostDto companyToCompanyPostDto(Company company);

    Company companyPostDtoToCompany(CompanyPostDto companyPostDto);

    CompanyGetDto companyToCompanyGetDto(Company company);

    Company companyGetDtoToCompany(CompanyGetDto companyGetDto);




}