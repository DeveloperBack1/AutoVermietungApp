package com.schneider.spring.springboot.autovermietungapp.mapper;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;

import java.util.List;

public interface CarMapper {

    List<CarDTO> toCarDTOList(List<Car> carList);
    Car toCar(CarDTO carDTO);
}