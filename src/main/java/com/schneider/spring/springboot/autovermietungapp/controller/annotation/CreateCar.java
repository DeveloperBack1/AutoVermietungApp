package com.schneider.spring.springboot.autovermietungapp.controller.annotation;

import com.schneider.spring.springboot.autovermietungapp.controller.handler.GlobalExceptionHandler;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
 * Annotation to specify the creation of a new car.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping
@Operation(
        summary = "Create new car",
        description = "Create new car and return it",
        tags = {"CAR"},
        requestBody = @RequestBody(
                description = "The car to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Car.class),
                        examples = {
                                @ExampleObject(name = "Good request",
                                        value = """
                                                {
                                                  "brand": "Toyota",
                                                  "model": "Corolla",
                                                  "year": 2021,
                                                  "color": "Red",
                                                  "price": 15000
                                                }
                                                """
                                ),
                                @ExampleObject(name = "Request with existing model",
                                        value = """ 
                                                {
                                                  "brand": "Toyota",
                                                  "model": "Corolla",
                                                  "year": 2020,
                                                  "color": "Blue",
                                                  "price": 12000
                                                }
                                                """
                                ),
                                @ExampleObject(name = "Not valid data",
                                        value = """ 
                                                {
                                                  "brand": "Toyota",
                                                  "model": "Corolla",
                                                  "year": "202X",  // Invalid year
                                                  "color": "Red",
                                                  "price": "15000X"  // Invalid price
                                                }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Car created successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Car.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "409",
                        description = "Car already exists",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = GlobalExceptionHandler.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid car data",
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
public @interface CreateCar {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}