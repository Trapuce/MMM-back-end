package matser2.istic.mmmback.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.*;


@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(mappedBy = "employees")
    private List<Worksite> worksites = new ArrayList<>();

}

