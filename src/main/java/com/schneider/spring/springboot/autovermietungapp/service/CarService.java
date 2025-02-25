package com.schneider.spring.springboot.autovermietungapp.service;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service interface for managing car-related operations.
 * <p>
 * This interface defines the contract for the Car service that includes methods for
 * retrieving, creating, and deleting cars, as well as filtering them by brand and model.
 * </p>
 */
@Service
public interface CarService {

    /**
     * Retrieves a list of all cars.
     * <p>
     * The result is a list of CarDTO representing all cars in the system.
     * </p>
     *
     * @return a list of CarDTO representing all cars
     */
    List<CarDTO> getAllCars();

    /**
     * Creates a new car based on the provided CarDTO.
     * <p>
     * The car is saved to the database and returned as a Car entity.
     * </p>
     *
     * @param carDTO the CarDTO containing the car data to create
     * @return the created Car entity
     */
    Car createCar(CarDTO carDTO);

    /**
     * Retrieves a list of cars filtered by the specified brand.
     * <p>
     * The result is a list of CarDTO representing cars with the specified brand.
     * </p>
     *
     * @param brand the brand of cars to filter by
     * @return a list of CarDTO representing the cars with the specified brand
     */
    List<CarDTO> getCarsByBrand(String brand);

    /**
     * Retrieves a list of cars filtered by the specified model.
     * <p>
     * The result is a list of CarDTO representing cars with the specified model.
     * </p>
     *
     * @param model the model of cars to filter by
     * @return a list of CarDTO representing the cars with the specified model
     */
    List<CarDTO> getCarsByModel(String model);

    /**
     * Deletes a car from the database by its ID.
     * <p>
     * If the car with the specified ID does not exist, an exception is thrown.
     * </p>
     *
     * @param id the ID of the car to delete
     */
    void deleteCarById(Integer id);
}
