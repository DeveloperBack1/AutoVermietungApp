package com.schneider.spring.springboot.autovermietungapp.entity;

import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entity representing a car available for rent in the Autovermietung system.
 */
@Getter
@Setter
@Entity
@Table(name = "cars")
@Schema(description = "Entity representing a car available for rent in the Autovermietung system.")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    @Schema(description = "Unique identifier for the car", example = "1", required = true)
    private int id;

    @Column(name = "car_model")
    @Schema(description = "The model of the car", example = "Model S", required = true)
    private String model;

    @Column(name = "car_brand")
    @Enumerated(EnumType.STRING)
    @Schema(description = "The brand of the car", example = "TESLA", required = true)
    private Brand brand;

    @Column(name = "car_price_per_day")
    @Schema(description = "The price of the car per day in the rental service", example = "199.99", required = true)
    private BigDecimal pricePerDay;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(model, car.model)
                && brand == car.brand
                && Objects.equals(pricePerDay, car.pricePerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, brand, pricePerDay);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand=" + brand +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
