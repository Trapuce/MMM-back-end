package matser2.istic.mmmback.mappers;

import javax.annotation.processing.Generated;
import matser2.istic.mmmback.DTO.AvailabilityDto;
import matser2.istic.mmmback.models.Availability;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-04T14:36:37+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class AvailabilityMapperImpl implements AvailabilityMapper {

    @Override
    public AvailabilityDto availabilitytoAvailabilityDto(Availability availability) {
        if ( availability == null ) {
            return null;
        }

        AvailabilityDto availabilityDto = new AvailabilityDto();

        availabilityDto.setId( availability.getId() );
        availabilityDto.setStartTime( availability.getStartTime() );
        availabilityDto.setEndTime( availability.getEndTime() );

        return availabilityDto;
    }

    @Override
    public Availability AvailabilityDtotoAvailability(AvailabilityDto availabilityDto) {
        if ( availabilityDto == null ) {
            return null;
        }

        Availability availability = new Availability();

        availability.setId( availabilityDto.getId() );
        availability.setStartTime( availabilityDto.getStartTime() );
        availability.setEndTime( availabilityDto.getEndTime() );

        return availability;
    }
}
