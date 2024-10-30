package matser2.istic.mmmback.mappers;

import matser2.istic.mmmback.DTO.ResourcesDto;
import matser2.istic.mmmback.DTO.WorksiteAllDto;
import matser2.istic.mmmback.DTO.WorksiteGetDto;
import matser2.istic.mmmback.DTO.WorksitePostDto;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.Worksite;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring",    uses = {ResourcesMapper.class, CompanyMapper.class}
)
public interface WorksiteMapper {
   /* @ObjectFactory
    default Worksite createWorksite(WorksiteAllDto dto) {
        return new Worksite();
    }

    @ObjectFactory
    default Worksite createWorksite(WorksitePostDto dto) {
        return new Worksite();
    }

    @ObjectFactory
    default Worksite createWorksite(WorksiteGetDto dto) {
        return new Worksite();
    }
    ResourcesDto resourceToResourceDto(Resources resource);
    Resources resourceDtoToResource(ResourcesDto resourceDto);

    @BeforeMapping
    default void beforeMapping(@MappingTarget Worksite target, WorksiteAllDto source) {
        if (target.getResources() == null) {
            target.setResources(new ArrayList<>());
        }
    }

    @BeforeMapping
    default void beforeMapping(@MappingTarget Worksite target, WorksitePostDto source) {
        if (target.getResources() == null) {
            target.setResources(new ArrayList<>());
        }
    }

    @BeforeMapping
    default void beforeMapping(@MappingTarget Worksite target, WorksiteGetDto source) {
        if (target.getResources() == null) {
            target.setResources(new ArrayList<>());
        }
    }*/
    WorksiteAllDto worksiteToWorksiteAllDto(Worksite worksite);

    Worksite worksiteAllDtoToWorksite(WorksiteAllDto worksiteAllDto);

    WorksitePostDto worksiteToWorksitePostDto(Worksite worksite);

    Worksite worksitePostDtoToWorksite(WorksitePostDto worksitePostDto);

    WorksiteGetDto worksiteToWorksiteGetDto(Worksite worksite);

    Worksite worksiteGetDtoToWorksite(WorksiteGetDto worksiteGetDto);

    List<ResourcesDto> resourcesToResourcesAllDto(List<Resources> resources);
}
