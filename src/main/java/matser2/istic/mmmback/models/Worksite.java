package matser2.istic.mmmback.models;

import jakarta.persistence.*;
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

    private String description;
    private Date startDate;
    private int durationInHalfDays;
    private String location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private WorksiteStatus status;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
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
}
