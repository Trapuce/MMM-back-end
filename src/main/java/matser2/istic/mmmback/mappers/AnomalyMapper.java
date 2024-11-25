package matser2.istic.mmmback.mappers;


import matser2.istic.mmmback.DTO.AnomalyDto;
import matser2.istic.mmmback.models.Anomaly;
import matser2.istic.mmmback.models.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnomalyMapper {
    AnomalyDto anomalyToAnomalyDto(Anomaly anomaly);




}