package com.schneider.spring.springboot.autovermietungapp.mapper.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import com.schneider.spring.springboot.autovermietungapp.mapper.CarMapper;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper implementation for converting between {@link Car} entities and {@link CarDTO} objects.
 * <p>
 * This class provides methods to convert a list of {@link Car} objects into a list of {@link CarDTO} objects
 * and vice versa, ensuring proper data mapping between the two.
 */
@Component
public class CarMapperImpl implements CarMapper {

    /**
     * Converts a list of {@link Car} entities to a list of {@link CarDTO} objects.
     *
     * @param carList the list of {@link Car} entities to be converted.
     * @return the corresponding list of {@link CarDTO} objects.
     */
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

    /**
     * Converts a {@link CarDTO} object to a {@link Car} entity.
     *
     * @param carDTO the {@link CarDTO} object to be converted.
     * @return the corresponding {@link Car} entity.
     */
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