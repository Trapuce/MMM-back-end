package matser2.istic.mmmback.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String registrationNumber;

    private String address;

  /*  @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;*/

    @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resources> resources = new ArrayList<>();
   /* public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setCompany(this);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setCompany(null);
    }*/


    public void addResource(Resources resource) {
        this.resources.add(resource);
        resource.setCompany(this);
    }

    public void removeResource(Resources resource) {
        this.resources.remove(resource);
        resource.setCompany(null);
    }

}

