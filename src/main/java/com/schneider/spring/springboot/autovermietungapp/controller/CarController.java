package com.schneider.spring.springboot.autovermietungapp.controller;

import com.schneider.spring.springboot.autovermietungapp.controller.annotation.*;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import com.schneider.spring.springboot.autovermietungapp.validation.ValidBrand;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing car-related operations.
 * <p>
 * This controller provides endpoints for retrieving, creating, and deleting cars.
 */
@Validated
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
    @GetAllCars(path = "/getAll")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    /**
     * Retrieves cars by their brand.
     *
     * @param brand the brand of the car
     * @return List of car DTOs matching the given brand
     */
    @GetCarsByBrand(path = "/getByBrand/{brand}")
    @GetMapping("/getByBrand/{brand}")
    public List<CarDTO> getCarsByBrand(@PathVariable @ValidBrand String brand) {
        return carService.getCarsByBrand(brand);
    }

    /**
     * Retrieves cars by their model.
     *
     * @param model the model of the car
     * @return List of car DTOs matching the given model
     */
    @GetCarsByModel(path = "/getByModel/{model}")
    @GetMapping("/getByModel/{model}")
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
    @CreateCar(path = "/create")
    public Car createCar(@RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    /**
     * Deletes a car by its ID.
     *
     * @param id the ID of the car to be deleted
     */
    @DeleteCar(path = "/delete/{id}")
    @DeleteMapping("/delete/{id}")
    public void deleteCarById(@PathVariable Integer id) {
        carService.deleteCarById(id);
    }

    /**
     * Updates an existing car by its ID.
     *
     * @param id     the ID of the car to be updated
     * @param carDTO the updated car data
     * @return the updated car entity
     */
    @UpdateCar(path = "/update/{id}")
    @PutMapping("/update/{id}")
    public Car updateCar(@PathVariable Integer id, @RequestBody CarDTO carDTO) {
        return carService.updateCar(id, carDTO);
    }
}