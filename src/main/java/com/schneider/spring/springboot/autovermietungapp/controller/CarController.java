package com.schneider.spring.springboot.autovermietungapp.controller;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("getAllEntity")
    public List<Car> getAllEntity() {
        return carService.getAllCarsEntity();
    }

    @GetMapping("/getAllDTO")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/create")
    public Car createCar(@RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    @GetMapping("/getCarByModel")
    public List<CarDTO> getCarByModel(@RequestParam String model) {
        return carService.findCarsByModel(model);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCarById(@PathVariable Integer id) { //delete
        carService.deleteById(id);
    }
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

