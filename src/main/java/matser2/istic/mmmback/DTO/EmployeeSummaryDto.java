package matser2.istic.mmmback.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import matser2.istic.mmmback.models.Role;

public class EmployeeSummaryDto extends ResourcesSimpleDto{

    @NotEmpty(message = "First name is required.")
    @Size(max = 50, message = "First name must not exceed 50 characters.")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Size(max = 50, message = "Last name must not exceed 50 characters.")
    private String lastName;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters.")
    private String password;

    @NotNull(message = "Role is required.")
    private Role role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
