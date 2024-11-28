package matser2.istic.mmmback.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server devServer = new Server()
                .url("http://localhost:8080")
                .description("Serveur de développement");

        Server prodServer = new Server()
                .url("https://159.65.20.48:8080")
                .description("Serveur de production");

        return new OpenAPI()
                .servers(Arrays.asList(devServer, prodServer))
                .info(new Info()
                        .title("Employee Management System API")
                        .version("1.0")
                        .description("API pour la gestion des employés"));
    }

}