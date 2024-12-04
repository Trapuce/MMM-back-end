package matser2.istic.mmmback.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import matser2.istic.mmmback.models.Company;
import matser2.istic.mmmback.models.WorksiteStatus;

import java.util.Date;
import java.util.List;

public class WorksitePostDto {

    private Long id;

    @NotEmpty(message = "Worksite title is required.")
    private String title;

    @NotEmpty(message = "Worksite description is required.")
    private String description;

    @NotNull(message = "Start date is required.")
    private Date startDate;

    @NotNull(message = "Status update date is required.")
    private Date statusUpdated;

    @Positive(message = "Duration must be a positive number.")
    private int duration;

    @NotNull(message = "Creation date is required.")
    private Date createdAt;

    @NotNull(message = "Longitude is required.")
    @Positive(message = "Longitude must be a positive number.")
    private int longitude;

    @NotNull(message = "Latitude is required.")
    @Positive(message = "Latitude must be a positive number.")
    private int latitude;

    @NotEmpty(message = "Location is required.")
    private String location;

    @NotNull(message = "Customer is required.")
    private CustomerPostDto customer;

    private List<PhotoDto> photos;


    private WorksiteStatus status;

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


    public CustomerPostDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerPostDto customer) {
        this.customer = customer;
    }

    public WorksiteStatus getStatus() {
        return status;
    }

    public void setStatus(WorksiteStatus status) {
        this.status = status;
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
