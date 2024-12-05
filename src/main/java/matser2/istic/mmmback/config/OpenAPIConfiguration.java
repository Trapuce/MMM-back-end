package matser2.istic.mmmback.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * OpenAPI Configuration for Swagger/SpringDoc documentation
 * Defines server environments and API metadata for API documentation
 */
@Configuration
public class OpenAPIConfiguration {


    /**
     * Configures OpenAPI specification with server details and API information
     *
     * @return Fully configured OpenAPI object
     */
    @Bean
    public OpenAPI defineOpenApi() {
        Server devServer = new Server()
                .url("http://localhost:8080")
                .description("Development server URL");

        Server prodServer = new Server()
                .url("http://159.65.20.48:8080")
                .description("Production server URL");

        return new OpenAPI()
                .servers(Arrays.asList(devServer, prodServer))
                .info(new Info()
                        .title("Worksite Management System API")
                        .version("1.0")
                        .description("Comprehensive API for worksite management and tracking"))
                .addSecurityItem(new SecurityRequirement().addList("bearerToken"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerToken", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")));
    }

}