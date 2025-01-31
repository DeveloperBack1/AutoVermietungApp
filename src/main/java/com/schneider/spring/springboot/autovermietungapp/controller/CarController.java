package com.schneider.spring.springboot.autovermietungapp.controller;

import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private  CarService carService;

    @GetMapping("/getAll")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }




}
