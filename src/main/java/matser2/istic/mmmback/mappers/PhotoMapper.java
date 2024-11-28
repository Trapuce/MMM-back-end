package matser2.istic.mmmback.mappers;

import matser2.istic.mmmback.DTO.PhotoDto;
import matser2.istic.mmmback.models.Photo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhotoMapper {


    PhotoDto photoToPhotoDto(Photo photo);


    Photo photoDtoToPhoto(PhotoDto photoDto);


    List<PhotoDto> photosToPhotoDtos(List<Photo> photos);

    List<Photo> photoDtosToPhotos(List<PhotoDto> photoDtos);
}