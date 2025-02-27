package com.schneider.spring.springboot.autovermietungapp.controller;

import com.schneider.spring.springboot.autovermietungapp.controller.annotation.*;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import com.schneider.spring.springboot.autovermietungapp.exception.IncorrectBrandNameException;
import com.schneider.spring.springboot.autovermietungapp.exception.errormessages.ErrorMessage;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import io.swagger.v3.oas.annotations.Parameter;
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
  @GetAllCars(path="/getAll")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    /**
     * Retrieves cars by their brand.
     *
     * @param brand the brand of the car
     * @return List of car DTOs matching the given brand
     */
    @GetCarsByBrand(path="/getByBrand/{brand}")
    public List<CarDTO> getCarsByBrand(@PathVariable String brand) {
        brandValidator(brand);
        return carService.getCarsByBrand(brand);
    }

    /**
     * Retrieves cars by their model.
     *
     * @param model the model of the car
     * @return List of car DTOs matching the given model
     */
    @GetCarsByModel(path="/getByModel/{model}")
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
    @DeleteCarById(path="/delete/{id}")
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