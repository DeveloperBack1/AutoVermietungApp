package com.schneider.spring.springboot.autovermietungapp.service.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.exception.CarsNotExistInDataBaseException;
import com.schneider.spring.springboot.autovermietungapp.exception.errorMessages.ErrorMessage;
import com.schneider.spring.springboot.autovermietungapp.mapper.CarMapper;
import com.schneider.spring.springboot.autovermietungapp.repository.CarRepository;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public List<CarDTO> getAllCars() {
        List<Car> list = carRepository.findAll();

        if (list.isEmpty()) {
            throw new CarsNotExistInDataBaseException(ErrorMessage.CARS_NOT_EXIST_IN_DATABASE);
        }
        return carMapper.toCarDTOList(list);
    }

    @Override
    public Car createCar(CarDTO carDTO) {
        return carRepository.saveAndFlush(carMapper.toCar(carDTO));
    }

    @Override
    public List<CarDTO> findCarsByModel(String model) {
        return List.of();
    }

    @Override
    public void deleteById(Integer id) {
     carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAllCarsEntity() {
        return carRepository.findAll();
    }
}