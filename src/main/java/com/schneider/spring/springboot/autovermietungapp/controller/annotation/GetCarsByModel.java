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
 * Annotation for retrieving cars by model.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping
@Operation(
        summary = "Get cars by model",
        description = "Retrieve cars based on the given model",
        tags = {"CAR"},
        parameters = @Parameter(
                name = "model",
                description = "The model of the car to filter by",
                required = true
        ),
        responses = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved cars"),
                @ApiResponse(responseCode = "400", description = "Invalid model parameter")
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface GetCarsByModel {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}