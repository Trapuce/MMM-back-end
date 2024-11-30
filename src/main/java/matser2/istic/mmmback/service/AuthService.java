package matser2.istic.mmmback.service;

import matser2.istic.mmmback.config.AuthenticationResponse;
import matser2.istic.mmmback.config.JwtService;
import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.repository.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ResourcesRepository resourcesRepository;


    public AuthenticationResponse authenticate(String email, String password) throws AuthenticationException {
        try {
            Employee employee = resourcesRepository.findEmployeeByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(email);

                return new AuthenticationResponse(token, employee.getId());
            } else {
                throw new AuthenticationException("Authentification échouée");
            }
        } catch (Exception e) {
            throw new AuthenticationException("Erreur d'authentification : " + e.getMessage());
        }
    }
}
