package com.schneider.spring.springboot.autovermietungapp.service;

import com.schneider.spring.springboot.autovermietungapp.entity.Rental;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentalService {
    public List<Rental> getAllRentals();


}
