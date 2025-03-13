package com.schneider.spring.springboot.autovermietungapp.mapper;

import com.schneider.spring.springboot.autovermietungapp.dto.RentalDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Rental;

public interface RentalMapper {

    RentalDTO toRentalDTO(Rental rental);

    Rental toRental(RentalDTO rentalDTO);
}