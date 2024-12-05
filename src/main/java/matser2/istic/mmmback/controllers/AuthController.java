package matser2.istic.mmmback.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import matser2.istic.mmmback.config.AuthenticationResponse;
import matser2.istic.mmmback.exceptions.InvalidCredentialsException;
import matser2.istic.mmmback.models.LoginRequest;
import matser2.istic.mmmback.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    @Autowired
    private AuthService authenticationService;

    /**
     * Authenticate a user with email and password.
     *
     * @param loginRequest The login request containing user credentials (email and password).
     * @return A ResponseEntity containing an AuthenticationResponse with token details.
     * @throws AuthenticationException if authentication fails.
     */
    @PostMapping("/login")
    @Operation(
            summary = "User Login",
            description = "Authenticate a user and return a JWT token upon successful login."
    )
    @ApiResponse(responseCode = "200", description = "Login successful, token returned")
    @ApiResponse(responseCode = "400", description = "Invalid login credentials")

    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody LoginRequest loginRequest) throws AuthenticationException {

        validateLoginRequest(loginRequest);

        AuthenticationResponse authResponse = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        return ResponseEntity.ok(authResponse);
    }

    /**
     * Validates the login request to ensure required fields are provided.
     *
     * @param loginRequest The login request object to validate.
     * @throws InvalidCredentialsException if any required field is missing or invalid.
     */
    private void validateLoginRequest(LoginRequest loginRequest) {
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isBlank()) {
            throw new InvalidCredentialsException("Email is required");
        }

        if (loginRequest.getPassword() == null || loginRequest.getPassword().isBlank()) {
            throw new InvalidCredentialsException("Password required");
        }
    }

}

