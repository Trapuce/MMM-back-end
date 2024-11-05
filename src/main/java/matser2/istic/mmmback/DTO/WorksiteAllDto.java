package matser2.istic.mmmback.DTO;

import matser2.istic.mmmback.models.WorksiteStatus;

import java.util.Date;
import java.util.List;

public class WorksiteAllDto {
    private Long id;
    private String description;
    private Date startDate;
    private int durationInHalfDays;
    private String location;
    private CustomerGetDto customer;
    private WorksiteStatus status;
    private List<ResourcesDto> resources;
    private CompanyGetDto company ;

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

    public int getDurationInHalfDays() {
        return durationInHalfDays;
    }

    public void setDurationInHalfDays(int durationInHalfDays) {
        this.durationInHalfDays = durationInHalfDays;
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

    public CompanyGetDto getCompany() {
        return company;
    }

    public void setCompany(CompanyGetDto company) {
        this.company = company;
    }
}
