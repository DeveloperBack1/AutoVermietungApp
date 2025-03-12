package com.schneider.spring.springboot.autovermietungapp.service.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.RentalDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Rental;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import com.schneider.spring.springboot.autovermietungapp.mapper.RentalMapper;
import com.schneider.spring.springboot.autovermietungapp.repository.RentalRepository;
import com.schneider.spring.springboot.autovermietungapp.service.RentalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public RentalServiceImpl(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    @Override
    public List<RentalDTO> getAllRentalsDTO() {
        List<Rental> rentals = rentalRepository.findAll();
        if (rentals.isEmpty()) {
            return Collections.emptyList();
        }

        return rentals.stream()
                .map(rentalMapper::toRentalDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RentalDTO> getRentalByIdDTO(int id) {
        return rentalRepository.findById(id)
                .map(rentalMapper::toRentalDTO);
    }

    @Override
    public boolean deleteRental(int id) {
        if (rentalRepository.existsById(id)) {
            rentalRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Rental with id " + id + " does not exist.");
        }
        return false;
    }

    @Transactional
    @Override
    public RentalDTO saveRentalDTO(RentalDTO rentalDTO) {
        Rental rental = rentalMapper.toRental(rentalDTO);
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.toRentalDTO(savedRental);
    }

    @Transactional
    @Override
    public Optional<RentalDTO> updateRentalDTO(int id, RentalDTO rentalDTO) {
        return rentalRepository.findById(id).map(existingRental -> {
            existingRental.setStartDate(rentalDTO.getStartDate());
            existingRental.setEndDate(rentalDTO.getEndDate());

            if (existingRental.getCar() != null) {
                existingRental.getCar().setModel(rentalDTO.getCarModel());
                existingRental.getCar().setBrand(Brand.valueOf(rentalDTO.getCarBrand().toUpperCase()));
            }

            if (existingRental.getUser() != null) {
                existingRental.getUser().setName(rentalDTO.getUserName());
                existingRental.getUser().setEmail(rentalDTO.getUserEmail());
            }

            rentalRepository.save(existingRental);

            return new RentalDTO(
                    existingRental.getId(),
                    existingRental.getStartDate(),
                    existingRental.getEndDate(),
                    existingRental.getTotalCost(),
                    existingRental.getCar() != null ? existingRental.getCar().getModel() : null,
                    existingRental.getCar() != null ? existingRental.getCar().getBrand().name() : null,
                    existingRental.getUser() != null ? existingRental.getUser().getName() : null,
                    existingRental.getUser() != null ? existingRental.getUser().getEmail() : null
            );
        });
    }
}
