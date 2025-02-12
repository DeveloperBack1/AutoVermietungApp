package com.schneider.spring.springboot.autovermietungapp.repository;

import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Optional<Car> findCarById(Integer id);
    Car deleteCarById(Integer id);
    List<Car> findCarByModel(String model);
    List<Car> findByBrand(Brand brand);
}