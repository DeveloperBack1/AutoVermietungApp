package com.schneider.spring.springboot.autovermietungapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for Car entity.
 * <p>
 * This class is used for transferring car details between the client and the server.
 */
@Setter
@Getter
@Schema(description = "Car data transfer object")
public class CarDTO {

    @JsonProperty("car_model")
    private String model;

    @JsonProperty("car_brand")
    private String brand;

    @JsonProperty("car_price_per_day")
    private String pricePerDay;

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