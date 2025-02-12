package com.schneider.spring.springboot.autovermietungapp.security.configure;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
        info = @Info(
                title = "Аренда автомобилей project Api",
                description = "API системы аренды автомобилей",
                version = "1.0.0",
                contact = @Contact(
                        name = "Max Schneider",
                        email = "max@yahoo.com",
                        url = "https://max schneider.dev"
                )
        )
)

public class OpenApiConfig {
    // http://localhost:8080/swagger-ui/index.html
}