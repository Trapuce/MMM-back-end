package matser2.istic.mmmback.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerPostDto {
    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Email")
    private String email;


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
}
