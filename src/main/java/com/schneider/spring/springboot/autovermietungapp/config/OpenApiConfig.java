package com.schneider.spring.springboot.autovermietungapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Autovermietung project Api",
                description = "API системы Autovermietung",
                version = "1.0.0",
                contact = @Contact(
                        name = "Max Schneider",
                        email = "max@yahoo.com",
                        url = "https://max schneider.dev"
                )
        )
)

public class OpenApiConfig {
// http://localhost:8088/swagger-ui/index.html
}