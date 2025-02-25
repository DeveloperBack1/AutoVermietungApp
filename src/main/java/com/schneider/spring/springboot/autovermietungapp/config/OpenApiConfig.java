package com.schneider.spring.springboot.autovermietungapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApiConfig sets up the OpenAPI documentation for the AutoVermietungApp.
 * <p>
 * This configuration customizes the API documentation's metadata, such as title, description,
 * version, contact information, and server URL.
 * </p>
 * <p>
 * Swagger UI will be available at:
 * {@code http://localhost:8088/swagger-ui/index.html}
 * </p>
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Autovermietung Project API",
                description = "API для управления системой аренды автомобилей (Autovermietung)",
                version = "1.0.0",
                contact = @Contact(
                        name = "Max Schneider",
                        email = "max@yahoo.com",
                        url = "https://maxschneider.dev"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8088",
                        description = "Local development server"
                ),
                @Server(
                        url = "https://api.autovermietungapp.com",
                        description = "Production server"
                )
        }
)
@Configuration
public class OpenApiConfig {
    // OpenAPI documentation configuration for the AutoVermietungApp.
    // Access Swagger UI at: http://localhost:8088/swagger-ui/index.html
}
