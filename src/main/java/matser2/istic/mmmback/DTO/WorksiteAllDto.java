package matser2.istic.mmmback.DTO;

import matser2.istic.mmmback.models.Photo;
import matser2.istic.mmmback.models.WorksiteStatus;

import java.util.Date;
import java.util.List;

public class WorksiteAllDto {
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date statusUpdated;
    private int duration;
    private Date createdAt;
    private int longitude;
    private int latitude;
    private String location;
    private WorksiteStatus status;
    private CustomerGetDto customer;
    private List<ResourcesDto> resources;
    private List<AnomalyDto> anomalies;
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


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public WorksiteStatus getStatus() {
        return status;
    }

    public void setStatus(WorksiteStatus status) {
        this.status = status;
    }

    public List<ResourcesDto> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesDto> resources) {
        this.resources = resources;
    }

    public CustomerGetDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerGetDto customer) {
        this.customer = customer;
    }


    public List<AnomalyDto> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<AnomalyDto> anomalies) {
        this.anomalies = anomalies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStatusUpdated() {
        return statusUpdated;
    }

    public void setStatusUpdated(Date statusUpdated) {
        this.statusUpdated = statusUpdated;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public List<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDto> photos) {
        this.photos = photos;
    }
}
