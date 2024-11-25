package matser2.istic.mmmback.DTO;

import matser2.istic.mmmback.models.Photo;

import java.util.List;

public class AnomalyDto {
    private Long id;
    private String description;
    private List<PhotoDto> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDto> photoPaths) {
        this.photos = photoPaths;
    }
}
