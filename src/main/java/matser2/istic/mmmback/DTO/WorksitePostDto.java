package matser2.istic.mmmback.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import matser2.istic.mmmback.models.WorksiteStatus;

import java.util.Date;

public class WorksitePostDto {

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Descripton")
    private String description;

    @JsonProperty("StartDate")
    private Date startDate;

    @JsonProperty("DurationHalfDays")
    private int durationInHalfDays;

    @JsonProperty("Location")
    private String location;
    @JsonProperty("customer")
    private CustomerPostDto customer;

    @JsonProperty("Status")
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

    public int getDurationInHalfDays() {
        return durationInHalfDays;
    }

    public void setDurationInHalfDays(int durationInHalfDays) {
        this.durationInHalfDays = durationInHalfDays;
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
}
