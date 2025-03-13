package com.schneider.spring.springboot.autovermietungapp.mapper.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.RentalDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.Rental;
import com.schneider.spring.springboot.autovermietungapp.entity.User;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import com.schneider.spring.springboot.autovermietungapp.mapper.RentalMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper implementation for converting between {@link Rental} entities and {@link RentalDTO} objects.
 */
@Component
public class RentalMapperImpl implements RentalMapper {

    /**
     * Converts a {@link Rental} entity to a {@link RentalDTO} object.
     *
     * @param rental the {@link Rental} entity to be converted.
     * @return the corresponding {@link RentalDTO} object.
     */
    @Override
    public RentalDTO toRentalDTO(Rental rental) {
        if (rental == null) {
            return null;
        }

        RentalDTO rentalDTO = new RentalDTO();

        rentalDTO.setId(rental.getId());
        rentalDTO.setStartDate(rental.getStartDate());
        rentalDTO.setEndDate(rental.getEndDate());
        rentalDTO.setTotalCost(rental.getTotalCost());

        if (rental.getCar() != null) {
            rentalDTO.setCarId(rental.getCar().getId()); // <-- добавил carId
            rentalDTO.setCarModel(rental.getCar().getModel());
            rentalDTO.setCarBrand(rental.getCar().getBrand().toString());
        }

        if (rental.getUser() != null) {
            rentalDTO.setUserName(rental.getUser().getName());
            rentalDTO.setUserEmail(rental.getUser().getEmail());
        }

        return rentalDTO;
    }


    /**
     * Converts a {@link RentalDTO} object to a {@link Rental} entity.
     *
     * @param rentalDTO the {@link RentalDTO} object to be converted.
     * @return the corresponding {@link Rental} entity.
     */
    @Override
    public Rental toRental(RentalDTO rentalDTO) {
        if (rentalDTO == null) {
            return null;
        }

        Rental rental = new Rental();

        rental.setId(rentalDTO.getId());
        rental.setStartDate(rentalDTO.getStartDate());
        rental.setEndDate(rentalDTO.getEndDate());

        rental.setTotalCost(rentalDTO.getTotalCost());

        Car car = new Car();
        car.setModel(rentalDTO.getCarModel());
        car.setBrand(Brand.valueOf(rentalDTO.getCarBrand().toUpperCase()));
        rental.setCar(car);

        User user = new User();
        user.setName(rentalDTO.getUserName());
        user.setEmail(rentalDTO.getUserEmail());
        rental.setUser(user);

        return rental;
    }
}