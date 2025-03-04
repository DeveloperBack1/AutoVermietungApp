package com.schneider.spring.springboot.autovermietungapp.controller.annotation;

import com.schneider.spring.springboot.autovermietungapp.controller.handler.GlobalExceptionHandler;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
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
 * Annotation to specify the update of an existing car.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping
@Operation(
        summary = "Update existing car",
        description = "Update car details by ID and return updated car",
        tags = {"CAR"},
        requestBody = @RequestBody(
                description = "Updated car details",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = CarDTO.class),
                        examples = {
                                @ExampleObject(name = "Valid request",
                                        value = """
                                                {
                                                  "brand": "BMW",
                                                  "model": "X5",
                                                  "pricePerDay": "75.00"
                                                }
                                                """
                                ),
                                @ExampleObject(name = "Invalid brand",
                                        value = """ 
                                                {
                                                  "brand": "UnknownBrand",
                                                  "model": "X5",
                                                  "pricePerDay": "75.00"
                                                }
                                                """
                                ),
                                @ExampleObject(name = "Invalid price format",
                                        value = """ 
                                                {
                                                  "brand": "BMW",
                                                  "model": "X5",
                                                  "pricePerDay": "seventy five"
                                                }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Car updated successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Car.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Car not found",
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
public @interface UpdateCar {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}

