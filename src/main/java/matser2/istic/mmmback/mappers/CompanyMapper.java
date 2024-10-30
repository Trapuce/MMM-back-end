package matser2.istic.mmmback.mappers;


import matser2.istic.mmmback.DTO.CompanyAllDto;
import matser2.istic.mmmback.DTO.CompanyPostDto;
import matser2.istic.mmmback.DTO.ResourcesDto;
import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.models.Resources;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;


@Mapper(
        componentModel = "spring"
)
public interface CompanyMapper {

    @ObjectFactory
    default Company createCompany(CompanyAllDto dto) {
        return new Company();
    }

    @ObjectFactory
    default Company createCompany(CompanyPostDto dto) {
        return new Company();
    }
    CompanyAllDto companyToCompanyAllDto(Company company);

    Company companyAllDtoToCompany(CompanyAllDto companyAllDto);

    CompanyPostDto companyToCompanyPostDto(Company company);

    Company companyPostDtoToCompany(CompanyPostDto companyPostDto);
    ResourcesDto resourceToResourceDto(Resources resource);
    Resources resourceDtoToResource(ResourcesDto resourceDto);


    /*@BeforeMapping
    default void beforeMapping(@MappingTarget Company target, CompanyAllDto source) {
        if (target.getResources() == null) {
            target.setResources(new ArrayList<>());
        }
    }*/
}