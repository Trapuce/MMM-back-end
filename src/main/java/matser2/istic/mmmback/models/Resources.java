package matser2.istic.mmmback.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type-resources")
public    class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column
    private String name;


    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "company_id")
    private Company company;


    @ManyToMany(mappedBy = "resources")
    private List<Worksite> worksites = new ArrayList<>();

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Worksite> getWorksites() {
        return worksites;
    }

    public void setWorksites(List<Worksite> worksites) {
        this.worksites = worksites;
    }
}
