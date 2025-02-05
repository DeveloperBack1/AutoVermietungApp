package com.schneider.spring.springboot.autovermietungapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarDTO {
    @JsonProperty("car_model")
    private String model;

    @JsonProperty("car_brand")
    private String brand;

    @JsonProperty("car_price_per_day")
    private String pricePerDay;

    public String getBrand() {
        return brand;
    }

    public CarDTO(String model, String brand, String pricePerDay) {
        this.model = model;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", pricePerDay='" + pricePerDay + '\'' +
                '}';
    }
}
