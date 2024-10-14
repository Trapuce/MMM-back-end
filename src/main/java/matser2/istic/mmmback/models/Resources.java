package matser2.istic.mmmback.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type-resources")
public abstract class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name;


    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "company_id")
    private Company company;


    @ManyToMany(mappedBy = "resources")
    private List<Worksite> worksites = new ArrayList<>();

}
