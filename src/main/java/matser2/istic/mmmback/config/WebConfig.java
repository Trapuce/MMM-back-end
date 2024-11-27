package matser2.istic.mmmback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Autorise toutes les routes
                .allowedOrigins("*") // Autorise toutes les origines
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Autorise toutes les méthodes HTTP
                .allowedHeaders("*") // Autorise tous les en-têtes
                .allowCredentials(false); // Désactive les cookies et l'authentification via sessions
    }
}
