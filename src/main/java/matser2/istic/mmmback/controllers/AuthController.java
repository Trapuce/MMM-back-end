package matser2.istic.mmmback.controllers;

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
public class AuthController {

    @Autowired
    private AuthService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody LoginRequest loginRequest) throws AuthenticationException {

        validateLoginRequest(loginRequest);

        AuthenticationResponse authResponse = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        return ResponseEntity.ok(authResponse);
    }

    private void validateLoginRequest(LoginRequest loginRequest) {
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isBlank()) {
            throw new InvalidCredentialsException("L'email est requis");
        }

        if (loginRequest.getPassword() == null || loginRequest.getPassword().isBlank()) {
            throw new InvalidCredentialsException("Le mot de passe est requis");
        }
    }

}

