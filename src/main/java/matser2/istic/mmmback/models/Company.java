package matser2.istic.mmmback.models;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "address")
    private String address;


    @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Worksite> worksiteList = new ArrayList<>() ;



    public void addWorksite(Worksite worksite){
        this.worksiteList.add(worksite);
        worksite.setCompany(this);
    }


    public void removeWorksite(Worksite worksite){
        this.worksiteList.remove(worksite);
        worksite.setCompany(null);
    }

    @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resources> resources = new ArrayList<>();

    public void addResource(Resources resource) {
        this.resources.add(resource);
        resource.setCompany(this);
    }

    public void removeResource(Resources resource) {
        this.resources.remove(resource);
        resource.setCompany(null);
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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Worksite> getWorksiteList() {
        return worksiteList;
    }

    public void setWorksiteList(List<Worksite> worksiteList) {
        this.worksiteList = worksiteList;
    }

    public List<Resources> getResources() {
        return resources;
    }

    public void setResources(List<Resources> resources) {
        this.resources = resources;
    }


}

