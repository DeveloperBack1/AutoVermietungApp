package com.schneider.spring.springboot.autovermietungapp.mapper.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import com.schneider.spring.springboot.autovermietungapp.mapper.CarMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    @Override
    public Car toCar(CarDTO carDTO) {
        Car car = new Car();
        car.setModel(carDTO.getModel());
        String brandFromDTO = carDTO.getBrand();
        Brand brand = Brand.valueOf(brandFromDTO.toUpperCase());
        car.setBrand(brand);
        String pricePerDayFromDTO = carDTO.getPricePerDay();
        BigDecimal pricePerDay = BigDecimal.valueOf(Double.parseDouble(pricePerDayFromDTO));
        car.setPricePerDay(pricePerDay);
        return car;
    }
}