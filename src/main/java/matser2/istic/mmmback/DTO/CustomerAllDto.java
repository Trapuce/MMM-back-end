package matser2.istic.mmmback.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import matser2.istic.mmmback.models.Worksite;

import java.util.ArrayList;
import java.util.List;

public class CustomerAllDto {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Email")
    private String email;
     @JsonProperty
    private String phoneNumber ;

    @JsonProperty("worksites")
    private List<Worksite> worksites = new ArrayList<>();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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
