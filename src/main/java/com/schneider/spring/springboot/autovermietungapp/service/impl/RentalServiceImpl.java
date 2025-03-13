package com.schneider.spring.springboot.autovermietungapp.service.impl;

import com.schneider.spring.springboot.autovermietungapp.dto.RentalDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.Rental;
import com.schneider.spring.springboot.autovermietungapp.entity.User;
import com.schneider.spring.springboot.autovermietungapp.mapper.RentalMapper;
import com.schneider.spring.springboot.autovermietungapp.repository.CarRepository;
import com.schneider.spring.springboot.autovermietungapp.repository.RentalRepository;
import com.schneider.spring.springboot.autovermietungapp.repository.UserRepository;
import com.schneider.spring.springboot.autovermietungapp.service.RentalService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private static final Logger logger = LoggerFactory.getLogger(RentalServiceImpl.class);

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public RentalServiceImpl(RentalRepository rentalRepository, RentalMapper rentalMapper,
                             UserRepository userRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @Override
    public List<RentalDTO> getAllRentalsDTO() {
        List<Rental> rentals = rentalRepository.findAll();
        if (rentals.isEmpty()) {
            return Collections.emptyList();
        }
        return rentals.stream()
                .map(rentalMapper::toRentalDTO)
                .toList();
    }

    @Override
    public Optional<RentalDTO> getRentalByIdDTO(int id) {
        return rentalRepository.findById(id)
                .map(rentalMapper::toRentalDTO);
    }

    @Override
    public RentalDTO updateRentalDTO(int id, RentalDTO rentalDTO) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with ID: " + id));

        Car car = carRepository.findById(rentalDTO.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found with ID: " + rentalDTO.getCarId()));

        rental.setCar(car);
        rental.setStartDate(rentalDTO.getStartDate());
        rental.setEndDate(rentalDTO.getEndDate());
        rental.setTotalCost(rentalDTO.getTotalCost());

        rental = rentalRepository.save(rental);

        return new RentalDTO(
                rental.getId(),
                rental.getCar().getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getTotalCost(),
                rental.getCar().getModel(),
                rental.getCar().getBrand().toString(),
                rentalDTO.getUserName(),
                rentalDTO.getUserEmail()
        );
    }

    @Transactional
    @Override
    public boolean deleteRental(int id) {
        if (rentalRepository.existsById(id)) {
            logger.info("Rental найден, выполняем удаление: {}", id);
            rentalRepository.deleteById(id);
            rentalRepository.flush();
            return true;
        } else {
            throw new IllegalArgumentException("Rental with id " + id + " does not exist.");
        }
    }

    @Override
    @Transactional
    public RentalDTO createRental(RentalDTO rentalDTO) {
        Car car = carRepository.findById(rentalDTO.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found with ID: " + rentalDTO.getCarId()));

        User user = userRepository.findByEmail(rentalDTO.getUserEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + rentalDTO.getUserEmail()));

        Rental rental = new Rental();
        rental.setCar(car);
        rental.setUser(user);
        rental.setStartDate(rentalDTO.getStartDate());
        rental.setEndDate(rentalDTO.getEndDate());
        rental.setTotalCost(rentalDTO.getTotalCost());

        rental = rentalRepository.save(rental);

        return new RentalDTO(
                rental.getId(),
                rental.getCar().getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getTotalCost(),
                rental.getCar().getModel(),
                rental.getCar().getBrand().name(),
                rental.getUser().getName(),
                rental.getUser().getEmail()
        );
    }
}











