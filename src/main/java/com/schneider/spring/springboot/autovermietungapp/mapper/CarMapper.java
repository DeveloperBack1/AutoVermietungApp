package com.schneider.spring.springboot.autovermietungapp.mapper;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;

import java.util.List;

/**
 * Mapper interface for converting between {@link Car} entities and {@link CarDTO} objects.
 * <p>
 * This interface defines methods for converting a list of {@link Car} entities to a list of {@link CarDTO}
 * objects and vice versa, ensuring proper data mapping between the two.
 */
public interface CarMapper {

    /**
     * Converts a list of {@link Car} entities to a list of {@link CarDTO} objects.
     *
     * @param carList the list of {@link Car} entities to be converted.
     * @return the corresponding list of {@link CarDTO} objects.
     */
    List<CarDTO> toCarDTOList(List<Car> carList);

    /**
     * Converts a {@link CarDTO} object to a {@link Car} entity.
     *
     * @param carDTO the {@link CarDTO} object to be converted.
     * @return the corresponding {@link Car} entity.
     */
    Car toCar(CarDTO carDTO);
}
