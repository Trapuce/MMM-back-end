package matser2.istic.mmmback.service;

import matser2.istic.mmmback.models.Employee;
import matser2.istic.mmmback.models.Resources;
import matser2.istic.mmmback.models.UserPrincipal;
import matser2.istic.mmmback.repository.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private ResourcesRepository resourcesRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = resourcesRepository.findEmployeeByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec l'email: " + username));

        return new UserPrincipal(employee);

    }
}
