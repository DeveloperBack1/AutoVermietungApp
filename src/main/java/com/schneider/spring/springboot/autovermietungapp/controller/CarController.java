package com.schneider.spring.springboot.autovermietungapp.controller;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import com.schneider.spring.springboot.autovermietungapp.exception.IncorrectBrandNameException;
import com.schneider.spring.springboot.autovermietungapp.exception.errorMessages.ErrorMessage;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling car-related operations.
 * Provides endpoints for managing cars, such as creating, retrieving, and deleting cars.
 */
@RestController
@RequestMapping("/cars")
@Tag(name = "Car Management", description = "Endpoints for managing cars in the Autovermietung system")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Get all cars", description = "Retrieves a list of all available cars.")
    @ApiResponse(responseCode = "200", description = "List of cars successfully retrieved",
            content = @Content(mediaType = "application/json"))
    @GetMapping("/getAll")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @Operation(summary = "Get cars by brand", description = "Retrieves a list of cars filtered by brand.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of cars for the specified brand retrieved successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid brand name provided", content = @Content)
    })
    @GetMapping("/getByBrand/{brand}")
    public List<CarDTO> getCarsByBrand(
            @Parameter(description = "Brand of the car (e.g., BMW, Audi)") @PathVariable String brand) {
        brandValidator(brand);
        return carService.getCarsByBrand(brand);
    }

    @Operation(summary = "Get cars by model", description = "Retrieves a list of cars filtered by model.")
    @ApiResponse(responseCode = "200", description = "List of cars for the specified model retrieved successfully",
            content = @Content(mediaType = "application/json"))
    @GetMapping("/getByModel/{model}")
    public List<CarDTO> getCarsByModel(
            @Parameter(description = "Model of the car (e.g., A4, X5)") @PathVariable String model) {
        return carService.getCarsByModel(model);
    }

    @Operation(summary = "Create a new car", description = "Adds a new car to the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Car created successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid car data provided", content = @Content)
    })
    @PostMapping("/create")
    public Car createCar(
            @Parameter(description = "Car details to be added") @RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    @Operation(summary = "Delete a car by ID", description = "Deletes a car from the database using its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Car deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Car with the specified ID not found", content = @Content)
    })
    @DeleteMapping(value = "/delete/{id}")
    public void deleteCarById(@PathVariable Integer id) {
        carService.deleteCarById(id);
    }

    /**
     * Validates if the provided brand exists in the predefined list of brands.
     *
     * @param brand the brand name to validate
     * @throws IncorrectBrandNameException if the brand name is invalid
     */
    private void brandValidator(String brand) {
        Brand[] brands = Brand.values();
        boolean isValid = false;

        for (Brand b : brands) {
            if (b.name().equalsIgnoreCase(brand)) {
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            throw new IncorrectBrandNameException(ErrorMessage.INCORRECT_BRAND_NAME);
        }
    }
}
