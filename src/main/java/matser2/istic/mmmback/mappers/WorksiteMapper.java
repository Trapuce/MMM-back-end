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
        componentModel = "spring"
)
public interface WorksiteMapper {

    WorksiteAllDto worksiteToWorksiteAllDto(Worksite worksite);

    Worksite worksiteAllDtoToWorksite(WorksiteAllDto worksiteAllDto);

    WorksitePostDto worksiteToWorksitePostDto(Worksite worksite);

    Worksite worksitePostDtoToWorksite(WorksitePostDto worksitePostDto);

    WorksiteGetDto worksiteToWorksiteGetDto(Worksite worksite);

    Worksite worksiteGetDtoToWorksite(WorksiteGetDto worksiteGetDto);

}
