package matser2.istic.mmmback.DTO;

import matser2.istic.mmmback.models.Photo;
import matser2.istic.mmmback.models.SeverityLevel;

import java.util.List;

public class AnomalyDto {
    private Long id;
    private String title;

    private String description;
    private SeverityLevel severityLevel;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }

    public List<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDto> photoPaths) {
        this.photos = photoPaths;
    }
}
