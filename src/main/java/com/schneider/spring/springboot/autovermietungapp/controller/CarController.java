package com.schneider.spring.springboot.autovermietungapp.controller;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import com.schneider.spring.springboot.autovermietungapp.exception.IncorrectBrandNameException;
import com.schneider.spring.springboot.autovermietungapp.exception.errorMessages.ErrorMessage;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing car-related operations.
 * <p>
 * This controller provides endpoints for retrieving, creating, and deleting cars.
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Retrieves all cars from the database.
     *
     * @return List of car DTOs
     */
    @GetMapping("/getAll")
    @Operation(summary = "Get all cars", description = "Fetches a list of all cars from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of cars"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    /**
     * Retrieves cars by their brand.
     *
     * @param brand the brand of the car
     * @return List of car DTOs matching the given brand
     */
    @GetMapping("/getByBrand/{brand}")
    @Operation(summary = "Get cars by brand", description = "Fetches a list of cars filtered by the provided brand.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved cars by brand"),
            @ApiResponse(responseCode = "400", description = "Bad request: incorrect brand name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<CarDTO> getCarsByBrand(
            @Parameter(description = "The brand of the car to filter by") @PathVariable String brand) {
        brandValidator(brand);
        return carService.getCarsByBrand(brand);
    }

    /**
     * Retrieves cars by their model.
     *
     * @param model the model of the car
     * @return List of car DTOs matching the given model
     */
    @GetMapping("/getByModel/{model}")
    @Operation(summary = "Get cars by model", description = "Fetches a list of cars filtered by the provided model.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved cars by model"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<CarDTO> getCarsByModel(
            @Parameter(description = "The model of the car to filter by") @PathVariable String model) {
        return carService.getCarsByModel(model);
    }

    /**
     * Creates a new car entry in the database.
     *
     * @param carDTO the car data to be added
     * @return the created car entity
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new car", description = "Creates a new car entry in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created new car"),
            @ApiResponse(responseCode = "400", description = "Bad request: invalid car data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Car createCar(@RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    /**
     * Deletes a car by its ID.
     *
     * @param id the ID of the car to be deleted
     */
    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Delete a car", description = "Deletes a car by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted car"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void deleteCarById(@PathVariable Integer id) {
        carService.deleteCarById(id);
    }

    /**
     * Validates the car brand.
     *
     * @param brand the brand to be validated
     * @throws IncorrectBrandNameException if the brand is not valid
     */
    private void brandValidator(String brand) {
        Brand[] brands = Brand.values();
        int count = 0;

        for (Brand b : brands) {
            if (b.name().equalsIgnoreCase(brand)) {
                count++;
            }
        }

        if (count == 0) {
            throw new IncorrectBrandNameException(ErrorMessage.INCORRECT_BRAND_NAME);
        }
    }
}