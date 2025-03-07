package com.schneider.spring.springboot.autovermietungapp.controller.annotation;

import com.schneider.spring.springboot.autovermietungapp.controller.handler.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify the deletion of a car by ID.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping
@Operation(
        summary = "Delete car by ID",
        description = "Deletes a car by its ID if it exists",
        tags = {"CAR"},
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Car deleted successfully"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Car not found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = GlobalExceptionHandler.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface DeleteCar {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}