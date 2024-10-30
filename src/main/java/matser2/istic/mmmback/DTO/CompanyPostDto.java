package matser2.istic.mmmback.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyPostDto {
    @JsonProperty("Id")
    private Long id;


    @JsonProperty("Name")
    private String name;

    @JsonProperty("RegistratioNumber")
    private String registrationNumber;

    @JsonProperty("Address")
    private String address;

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
}
