package com.schneider.spring.springboot.autovermietungapp.controller;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CarApiDocs {

    @GetMapping("/getAll")
    @Operation(summary = "Get all cars", description = "Fetches a list of all cars from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of cars"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    List<CarDTO> getAllCars();

    @GetMapping("/getByBrand/{brand}")
    @Operation(summary = "Get cars by brand", description = "Fetches a list of cars filtered by the provided brand.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved cars by brand"),
            @ApiResponse(responseCode = "400", description = "Bad request: incorrect brand name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    List<CarDTO>  getCarsByBrand(@PathVariable String brand);

    @GetMapping("/getByModel/{model}")
    @Operation(summary = "Get cars by model", description = "Fetches a list of cars filtered by the provided model.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved cars by model"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    List<CarDTO> getCarsByModel(@PathVariable String model);

    @PostMapping("/create")
    @Operation(summary = "Create a new car", description = "Creates a new car entry in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created new car"),
            @ApiResponse(responseCode = "400", description = "Bad request: invalid car data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    Car createCar(CarDTO carDTO);

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a car", description = "Deletes a car by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted car"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    void deleteCarById(@PathVariable Integer id);
}

