package com.schneider.spring.springboot.autovermietungapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object representing a car in the Autovermietung system.
 */
@Setter
@Getter
@Schema(description = "Data Transfer Object for Car details")
public class CarDTO {

    @JsonProperty("car_model")
    @Schema(description = "The model of the car", example = "A4", required = true)
    private String model;

    @JsonProperty("car_brand")
    @Schema(description = "The brand of the car", example = "Audi", required = true)
    private String brand;

    @JsonProperty("car_price_per_day")
    @Schema(description = "The price to rent the car per day (in EUR)", example = "50.00", required = true)
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
