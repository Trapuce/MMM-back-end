package matser2.istic.mmmback.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import matser2.istic.mmmback.models.WorksiteStatus;

import java.util.Date;
import java.util.List;

public class WorksiteGetDto {

    private Long id;
    private String description;

    private Date startDate;

    private int durationInHalfDays;

    private String location;


    private WorksiteStatus status;

    private List<AnomalyDto> anomalies;

    public List<AnomalyDto> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<AnomalyDto> anomalies) {
        this.anomalies = anomalies;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDurationInHalfDays() {
        return durationInHalfDays;
    }

    public void setDurationInHalfDays(int durationInHalfDays) {
        this.durationInHalfDays = durationInHalfDays;
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
}
