package com.schneider.spring.springboot.autovermietungapp.service;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CarService {
    List<CarDTO> getAllCars();
    Car createCar(CarDTO carDTO);
    List<CarDTO> getCarsByModel(String model);
    List<CarDTO> getCarsByBrand(String brand);
}