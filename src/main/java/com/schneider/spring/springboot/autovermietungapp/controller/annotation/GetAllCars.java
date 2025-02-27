package com.schneider.spring.springboot.autovermietungapp.controller.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for retrieving all cars.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping
@Operation(
        summary = "Get all cars",
        description = "Retrieve a list of all cars",
        tags = {"CAR"},
        responses = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved list of cars"),
                @ApiResponse(responseCode = "500", description = "Internal server error")
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface GetAllCars {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}