package com.schneider.spring.springboot.autovermietungapp.controller;

import com.schneider.spring.springboot.autovermietungapp.dto.RentalDTO;
import com.schneider.spring.springboot.autovermietungapp.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/")
    public List<RentalDTO> getAllRentals() {
        return rentalService.getAllRentalsDTO();
    }

    @GetMapping("/{id}")
    public Optional<RentalDTO> getRentalById(@PathVariable int id) {
        return rentalService.getRentalByIdDTO(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public RentalDTO createRental(@RequestBody RentalDTO rentalDTO) {
        return rentalService.createRental(rentalDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public RentalDTO updateRental(@PathVariable int id, @RequestBody RentalDTO rentalDTO) {
        return rentalService.updateRentalDTO(id, rentalDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteRental(@PathVariable int id) {
        return rentalService.deleteRental(id);
    }


}