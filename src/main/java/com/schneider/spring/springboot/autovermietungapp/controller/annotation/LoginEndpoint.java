package com.schneider.spring.springboot.autovermietungapp.controller.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for the user login endpoint.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping
@Operation(
        summary = "User Login",
        description = "Authenticates a user with the provided credentials and returns a JWT token.",
        tags = {"Authentication"},
        requestBody = @RequestBody(
                description = "Login credentials containing email and password",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = com.schneider.spring.springboot.autovermietungapp.dto.LoginRequest.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully authenticated and JWT token generated",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = java.util.Map.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad request: invalid login credentials",
                        content = @Content(mediaType = "application/json")
                ),
                @ApiResponse(
                        responseCode = "401",
                        description = "Unauthorized: authentication failed",
                        content = @Content(mediaType = "application/json")
                )
        },
        security = {
                @SecurityRequirement(name = "BearerAuth")
        }
)
public @interface LoginEndpoint {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {"/login"};
}