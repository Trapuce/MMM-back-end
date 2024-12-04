package matser2.istic.mmmback.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "Customer name is required.")
    private String name;

    @Column
    @NotEmpty(message = "Customer email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @Column(name = "phone_number")
    @Positive(message = "Phone number must be a positive number.")
    private int phoneNumber;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    private List<Worksite> worksites = new ArrayList<>();


    public void addWorksite(Worksite worksite) {
        worksites.add(worksite);
        worksite.setCustomer(this);
    }
    public void removeWorksite(Worksite worksite) {
        worksites.remove(worksite);
        worksite.setCustomer(null);
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Worksite> getWorksites() {
        return worksites;
    }

    public void setWorksites(List<Worksite> worksites) {
        this.worksites = worksites;
    }
}
