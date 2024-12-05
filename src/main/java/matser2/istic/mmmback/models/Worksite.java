package matser2.istic.mmmback.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


    @Data
    @Entity
    public class Worksite {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        @NotEmpty(message = "Worksite title is required.")
        private String title;

        @Column(name = "description")
        @NotEmpty(message = "Worksite description is required.")
        private String description;

        @Column(name = "start_date")
        @NotNull(message = "Start date is required.")
        private Date startDate;

        @Column(name = "status_updated")
        @NotNull(message = "Status updated date is required.")
        private Date statusUpdated;

        @Column(name = "duration")
        @Positive(message = "Duration must be a positive number.")
        private int duration;

        @Column(name = "created_at")
        @NotNull(message = "Creation date is required.")
        private Date createdAt;

        @Column
        private double longitude;

        @Column
        private double latitude;

        @Column(name = "location")
        @NotEmpty(message = "Location is required.")
        private String location;

        @ManyToOne
        @JoinColumn(name = "customer_id")
        @NotNull(message = "Customer is required.")
        private Customer customer;

        @Enumerated(EnumType.STRING)
        @NotNull(message = "Worksite status is required.")
        private WorksiteStatus status;


        @ManyToMany(
                fetch = FetchType.LAZY
        )
        @JoinTable(
                name = "resources_worksite",
                joinColumns = @JoinColumn(name = "worksite_id"),
                inverseJoinColumns = @JoinColumn(name = "resources_id")
        )
        private List<Resources> resources = new ArrayList<>();

        @OneToMany(
                mappedBy = "worksite",
                cascade = CascadeType.ALL,
                orphanRemoval = true
        )
        private List<Anomaly> anomalies = new ArrayList<>();


        @OneToMany(mappedBy = "worksite" , cascade = CascadeType.ALL , orphanRemoval = true)
        private List<Photo> photos = new ArrayList<>();


    public void addPhoto(Photo photo) {
        photos.add(photo);
        photo.setWorksite(this);
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
        photo.setWorksite(null);
    }

    public void addAnomaly(Anomaly anomaly) {
        anomalies.add(anomaly);
        anomaly.setWorksite(this);
    }

    public void removeAnomaly(Anomaly anomaly) {
        anomalies.remove(anomaly);
        anomaly.setWorksite(null);
    }

    public void addResources(Resources resource) {
        resources.add(resource);
        resource.getWorksites().add(this);
    }
    public void removeResources(Resources resource) {
        resources.remove(resource);
        resource.getWorksites().remove(this);
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public WorksiteStatus getStatus() {
        return status;
    }

    public void setStatus(WorksiteStatus status) {
        this.status = status;
    }

    public List<Resources> getResources() {
        return resources;
    }

    public void setResources(List<Resources> resources) {
        this.resources = resources;
    }


    public List<Anomaly> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<Anomaly> anomalies) {
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
