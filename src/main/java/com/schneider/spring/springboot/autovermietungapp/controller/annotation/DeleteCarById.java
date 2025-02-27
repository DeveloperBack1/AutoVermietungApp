package com.schneider.spring.springboot.autovermietungapp.controller.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for deleting a car by ID.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping
@Operation(
        summary = "Delete a car by ID",
        description = "Deletes a car from the database using its ID",
        tags = {"CAR"},
        parameters = @Parameter(
                name = "id",
                description = "The ID of the car to be deleted",
                required = true
        ),
        responses = {
                @ApiResponse(responseCode = "200", description = "Car deleted successfully"),
                @ApiResponse(responseCode = "404", description = "Car not found"),
                @ApiResponse(responseCode = "500", description = "Internal server error")
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface DeleteCarById {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}