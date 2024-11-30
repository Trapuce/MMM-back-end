package matser2.istic.mmmback.config;

import matser2.istic.mmmback.DTO.AuthenticatedEmployeeDto;

public class AuthenticationResponse {
    private String token;
    private AuthenticatedEmployeeDto employee;

    public AuthenticationResponse(String token, AuthenticatedEmployeeDto employee) {
        this.token = token;
        this.employee = employee;
    }

    // Getters et setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthenticatedEmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(AuthenticatedEmployeeDto employee) {
        this.employee = employee;
    }
}
