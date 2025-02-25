package com.schneider.spring.springboot.autovermietungapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * OpenAPI configuration for the Autovermietung project API.
 * <p>
 * This configuration provides metadata for the API including the title, description, version,
 * and contact information.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Autovermietung project API",
                description = "API системы Autovermietung",
                version = "1.0.0",
                contact = @Contact(
                        name = "Max Schneider",
                        email = "max@yahoo.com",
                        url = "https://maxschneider.dev"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        )
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful response",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Bad Request",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
                content = @Content(mediaType = "application/json"))
})
public class OpenApiConfig {
}
