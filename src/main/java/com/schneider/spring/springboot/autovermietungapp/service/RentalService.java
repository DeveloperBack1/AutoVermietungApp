package com.schneider.spring.springboot.autovermietungapp.service;

import com.schneider.spring.springboot.autovermietungapp.dto.RentalDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface RentalService {

    boolean deleteRental(int id);

    public RentalDTO createRental(RentalDTO rentalDTO);

    List<RentalDTO> getAllRentalsDTO();

    Optional<RentalDTO> getRentalByIdDTO(int id);

   RentalDTO updateRentalDTO(int id, RentalDTO rentalDTO);

}