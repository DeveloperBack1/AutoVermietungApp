package com.schneider.spring.springboot.autovermietungapp.service.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import com.schneider.spring.springboot.autovermietungapp.exception.CarsNotExistInDataBaseException;
import com.schneider.spring.springboot.autovermietungapp.exception.errorMessages.ErrorMessage;
import com.schneider.spring.springboot.autovermietungapp.mapper.CarMapper;
import com.schneider.spring.springboot.autovermietungapp.repository.CarRepository;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing car-related operations.
 * <p>
 * This class implements the {@link CarService} interface and provides the business logic
 * for interacting with car data, including retrieving, creating, and deleting cars from the database.
 * </p>
 */
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    /**
     * Constructs a new CarServiceImpl with the provided repository and mapper.
     *
     * @param carRepository the repository to access car data
     * @param carMapper     the mapper to convert between entities and DTOs
     */
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    /**
     * Retrieves a list of all cars.
     * <p>
     * If no cars exist in the database, a {@link CarsNotExistInDataBaseException} is thrown.
     * </p>
     *
     * @return a list of CarDTO representing all cars
     */
    @Override
    public List<CarDTO> getAllCars() {
        List<Car> list = carRepository.findAll();

        if (list.isEmpty()) {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
        return carMapper.toCarDTOList(list);
    }

    /**
     * Creates a new car from the provided CarDTO.
     * <p>
     * The car is saved in the database and returned as a Car entity.
     * </p>
     *
     * @param carDTO the CarDTO containing the car data to create
     * @return the created Car entity
     */
    @Override
    public Car createCar(CarDTO carDTO) {
        return carRepository.saveAndFlush(carMapper.toCar(carDTO));
    }

    /**
     * Retrieves a list of cars filtered by brand.
     * <p>
     * If no cars with the specified brand are found, a {@link CarsNotExistInDataBaseException} is thrown.
     * </p>
     *
     * @param brand the brand of cars to filter by
     * @return a list of CarDTO representing the cars with the specified brand
     */
    @Override
    public List<CarDTO> getCarsByBrand(String brand) {
        Brand brandUppercase = Brand.valueOf(brand.toUpperCase());
        List<Car> list = carRepository.findByBrand(brandUppercase);

        if (list.isEmpty()) {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
        return carMapper.toCarDTOList(list);
    }

    /**
     * Retrieves a list of cars filtered by model.
     * <p>
     * If no cars with the specified model are found, a {@link CarsNotExistInDataBaseException} is thrown.
     * </p>
     *
     * @param model the model of cars to filter by
     * @return a list of CarDTO representing the cars with the specified model
     */
    @Override
    public List<CarDTO> getCarsByModel(String model) {
        List<Car> list = carRepository.findCarsByModel(model);
        if (list.isEmpty()) {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
        return carMapper.toCarDTOList(list);
    }

    /**
     * Deletes a car from the database by its ID.
     * <p>
     * If the car does not exist in the database, a {@link CarsNotExistInDataBaseException} is thrown.
     * </p>
     *
     * @param id the ID of the car to delete
     */
    @Override
    public void deleteCarById(Integer id) {
        Optional<Car> carForDeleting = carRepository.findCarById(id);

        if (carForDeleting.isPresent()) {
            carRepository.delete(carForDeleting.get());
        } else {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
    }
}