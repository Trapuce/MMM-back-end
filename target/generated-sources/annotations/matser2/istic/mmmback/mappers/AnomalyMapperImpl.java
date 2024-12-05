package matser2.istic.mmmback.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import matser2.istic.mmmback.DTO.AnomalyDto;
import matser2.istic.mmmback.DTO.PhotoDto;
import matser2.istic.mmmback.models.Anomaly;
import matser2.istic.mmmback.models.Photo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-05T13:22:29+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class AnomalyMapperImpl implements AnomalyMapper {

    @Override
    public AnomalyDto anomalyToAnomalyDto(Anomaly anomaly) {
        if ( anomaly == null ) {
            return null;
        }

        AnomalyDto anomalyDto = new AnomalyDto();

        anomalyDto.setId( anomaly.getId() );
        anomalyDto.setDescription( anomaly.getDescription() );
        anomalyDto.setTitle( anomaly.getTitle() );
        anomalyDto.setSeverityLevel( anomaly.getSeverityLevel() );
        anomalyDto.setPhotos( photoListToPhotoDtoList( anomaly.getPhotos() ) );

        return anomalyDto;
    }

    @Override
    public Anomaly anomalyDtoToAnomaly(AnomalyDto anomalyDto) {
        if ( anomalyDto == null ) {
            return null;
        }

        Anomaly anomaly = new Anomaly();

        anomaly.setId( anomalyDto.getId() );
        anomaly.setDescription( anomalyDto.getDescription() );
        anomaly.setTitle( anomalyDto.getTitle() );
        anomaly.setSeverityLevel( anomalyDto.getSeverityLevel() );
        anomaly.setPhotos( photoDtoListToPhotoList( anomalyDto.getPhotos() ) );

        return anomaly;
    }

    @Override
    public List<AnomalyDto> anomaliesToAnomalyDtos(List<Anomaly> anomalies) {
        if ( anomalies == null ) {
            return null;
        }

        List<AnomalyDto> list = new ArrayList<AnomalyDto>( anomalies.size() );
        for ( Anomaly anomaly : anomalies ) {
            list.add( anomalyToAnomalyDto( anomaly ) );
        }

        return list;
    }

    @Override
    public List<Anomaly> anomalyDtosToAnomalies(List<AnomalyDto> anomalyDtos) {
        if ( anomalyDtos == null ) {
            return null;
        }

        List<Anomaly> list = new ArrayList<Anomaly>( anomalyDtos.size() );
        for ( AnomalyDto anomalyDto : anomalyDtos ) {
            list.add( anomalyDtoToAnomaly( anomalyDto ) );
        }

        return list;
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
}
