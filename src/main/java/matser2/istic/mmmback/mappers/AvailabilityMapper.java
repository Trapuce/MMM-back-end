package matser2.istic.mmmback.mappers;

import matser2.istic.mmmback.DTO.AvailabilityDto;
import matser2.istic.mmmback.models.Availability;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvailabilityMapper {

    AvailabilityDto availabilitytoAvailabilityDto(Availability availability);


    Availability AvailabilityDtotoAvailability(AvailabilityDto availabilityDto);
}
