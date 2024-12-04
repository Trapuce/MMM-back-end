package matser2.istic.mmmback.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class CustomerPostDto {
    private Long id;

    @NotEmpty(message = "Customer name is required.")
    private String name;

    @NotEmpty(message = "Customer email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotEmpty(message = "Phone number is required.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits.")
    private String phoneNumber;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
