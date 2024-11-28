package matser2.istic.mmmback.models;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type-resources")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type_resources")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Employee.class, name = "EMPLOYEE"),
        @JsonSubTypes.Type(value = Equipment.class, name = "EQUIPMENT"),
        @JsonSubTypes.Type(value = Vehicle.class, name = "VEHICLE")
})
public abstract  class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column
    private String name;





    @ManyToMany(mappedBy = "resources" )
    private List<Worksite> worksites = new ArrayList<>();

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Availability> availabilities = new ArrayList<>();


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



    public List<Worksite> getWorksites() {
        return worksites;
    }

    public void setWorksites(List<Worksite> worksites) {
        this.worksites = worksites;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}
