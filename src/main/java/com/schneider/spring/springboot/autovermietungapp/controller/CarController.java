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

    @GetMapping("/getAll")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/create")
    public Car createCar(@RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    @GetMapping("/getByModel/{model}")
    public List<CarDTO> getCarsByModel(@PathVariable String model) {
        return carService.getCarsByModel(model);
    }

    @GetMapping("/getByBrand/{brand}")
    public List<CarDTO> getCarsByBrand(@PathVariable String brand) {
        return carService.getCarsByBrand(brand);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Integer id) {
        Car deletedCar = carService.deleteCarById(id);
        if (deletedCar == null) {
            return ResponseEntity.notFound().build(); // Возвращаем 404, если машина не найдена
        }
        return ResponseEntity.noContent().build(); // Возвращаем 204 No Content, если удаление успешно
    }

    @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }