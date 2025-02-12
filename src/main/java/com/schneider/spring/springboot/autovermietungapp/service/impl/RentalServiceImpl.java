package com.schneider.spring.springboot.autovermietungapp.service.impl;

import com.schneider.spring.springboot.autovermietungapp.entity.Rental;
import com.schneider.spring.springboot.autovermietungapp.repository.CarRepository;
import com.schneider.spring.springboot.autovermietungapp.repository.RentalRepository;
import com.schneider.spring.springboot.autovermietungapp.repository.UserRepository;

import com.schneider.spring.springboot.autovermietungapp.service.RentalService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final UserRepository customerRepository;

    public RentalServiceImpl(RentalRepository rentalRepository, CarRepository carRepository,UserRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

        @Override
        public List<Rental> getAllRentals() {
            return rentalRepository.findAll();
    }
}