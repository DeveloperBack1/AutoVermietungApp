package com.schneider.spring.springboot.autovermietungapp.service.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.exception.CarsNotExistInDataBaseException;
import com.schneider.spring.springboot.autovermietungapp.exception.errorMessages.ErrorMessage;
import com.schneider.spring.springboot.autovermietungapp.mapper.CarMapper;
import com.schneider.spring.springboot.autovermietungapp.repository.CarRepository;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




}
