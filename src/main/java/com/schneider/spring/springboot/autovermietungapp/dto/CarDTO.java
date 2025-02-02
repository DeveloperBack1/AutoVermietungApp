package com.schneider.spring.springboot.autovermietungapp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CarDTO {
    private String model;
    private String brand;
    private String pricePerDay;

    public CarDTO(String model, String brand, String pricePerDay) {
        this.model = model;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
    }
}
