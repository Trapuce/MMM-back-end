package matser2.istic.mmmback.DTO;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompanyAllDto {
    @JsonProperty("Id")
    private Long id;


    @JsonProperty("Name")
    private String name;

    @JsonProperty("RegistratioNumber")
    private String registrationNumber;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("Resources")
    private List<ResourcesDto> resources ;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ResourcesDto> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesDto> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", address='" + address + '\'' +
                ", resources=" + resources +
                '}';
    }
}