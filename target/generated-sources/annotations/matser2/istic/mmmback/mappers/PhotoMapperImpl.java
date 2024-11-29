package matser2.istic.mmmback.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import matser2.istic.mmmback.DTO.PhotoDto;
import matser2.istic.mmmback.models.Photo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T14:48:18+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class PhotoMapperImpl implements PhotoMapper {

    @Override
    public PhotoDto photoToPhotoDto(Photo photo) {
        if ( photo == null ) {
            return null;
        }

        PhotoDto photoDto = new PhotoDto();

        photoDto.setId( photo.getId() );
        photoDto.setFilePath( photo.getFilePath() );

        return photoDto;
    }

    @Override
    public Photo photoDtoToPhoto(PhotoDto photoDto) {
        if ( photoDto == null ) {
            return null;
        }

        Photo photo = new Photo();

        photo.setId( photoDto.getId() );
        photo.setFilePath( photoDto.getFilePath() );

        return photo;
    }

    @Override
    public List<PhotoDto> photosToPhotoDtos(List<Photo> photos) {
        if ( photos == null ) {
            return null;
        }

        List<PhotoDto> list = new ArrayList<PhotoDto>( photos.size() );
        for ( Photo photo : photos ) {
            list.add( photoToPhotoDto( photo ) );
        }

        return list;
    }

    @Override
    public List<Photo> photoDtosToPhotos(List<PhotoDto> photoDtos) {
        if ( photoDtos == null ) {
            return null;
        }

        List<Photo> list = new ArrayList<Photo>( photoDtos.size() );
        for ( PhotoDto photoDto : photoDtos ) {
            list.add( photoDtoToPhoto( photoDto ) );
        }

        return list;
    }
}
