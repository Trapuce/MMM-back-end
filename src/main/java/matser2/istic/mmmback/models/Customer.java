package matser2.istic.mmmback.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;


    @OneToMany(mappedBy = "customer")
    private List<Worksite> worksites = new ArrayList<>();


    public void addWorksite(Worksite worksite) {
        worksites.add(worksite);
        worksite.setCustomer(this);
    }
    public void removeWorksite(Worksite worksite) {
        worksites.remove(worksite);
        worksite.setCustomer(null);
    }






}
