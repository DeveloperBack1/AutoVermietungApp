package com.schneider.spring.springboot.autovermietungapp.controller;
import com.schneider.spring.springboot.autovermietungapp.entity.Rental;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import com.schneider.spring.springboot.autovermietungapp.service.RentalService;
import com.schneider.spring.springboot.autovermietungapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final UserService userService;
    private final CarService carService;

    public RentalController(RentalService rentalService, UserService userService, CarService carService) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping("/getAll")
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

//    @PostMapping
//    public ResponseEntity<Rental> createRental(@RequestBody Rental rental) {
//        Rental savedRental = rentalService.saveRental(rental);
//        return new ResponseEntity<>(savedRental, HttpStatus.CREATED);
//    }


}
