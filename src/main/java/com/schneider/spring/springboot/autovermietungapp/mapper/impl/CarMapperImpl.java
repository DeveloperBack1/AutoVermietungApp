package com.schneider.spring.springboot.autovermietungapp.mapper.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.mapper.CarMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarMapperImpl implements CarMapper {
    @Override
    public List<CarDTO> toCarDTOList(List<Car> carList) {

        List<CarDTO> carDTOList = new ArrayList<>();

        for (Car car : carList) {
            String model = car.getModel();
            String brand = car.getBrand().toString();
            String pricePerDay = String.valueOf(car.getPricePerDay());

            carDTOList.add(new CarDTO(model, brand, pricePerDay));
        }
        return carDTOList;
    }
}
