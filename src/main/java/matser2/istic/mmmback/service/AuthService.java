package matser2.istic.mmmback.service;

import matser2.istic.mmmback.config.JwtService;
import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.repository.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ResourcesRepository resourcesRepository;



    public String authenticate(String email, String password) {
        try {
            System.out.println("Authenticating user with email: " + email);
            System.out.println("Authenticating user with email: " + password);
            Employee employee = (Employee) resourcesRepository.findEmployeeByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            if (authentication.isAuthenticated()) {
                System.out.println("Authentication successful for user: " + email);
                return jwtService.generateToken(email);
            } else {
                System.out.println("Authentication failed for user: " + email);
                return "failed";
            }
        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
            return "failed";
        }

    }
}
