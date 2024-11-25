package matser2.istic.mmmback.controllers;

import matser2.istic.mmmback.models.LoginRequest;
import matser2.istic.mmmback.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authenticationService;



    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginRequest) {
        String jwt = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(jwt);
    }
}

