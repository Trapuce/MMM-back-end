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
            name = "employee_worksite",
            joinColumns = @JoinColumn(name = "worksite_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees = new ArrayList<>();



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

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.getWorksites().add(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.getWorksites().remove(this);
    }

    public void addResources(Resources resource) {
        resources.add(resource);
        resource.getWorksites().add(this);
    }
    public void removeResources(Resources resource) {
        resources.remove(resource);
        resource.getWorksites().remove(this);
    }
}
