package matser2.istic.mmmback.models;

import jakarta.persistence.*;
import lombok.Data;
import matser2.istic.mmmback.DTO.CompanyAllDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Worksite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "duration_in_half_days")
    private int durationInHalfDays;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private WorksiteStatus status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "resources_worksite",
            joinColumns = @JoinColumn(name = "worksite_id"),
            inverseJoinColumns = @JoinColumn(name = "resources_id")
    )
    private List<Resources> resources = new ArrayList<>();

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
