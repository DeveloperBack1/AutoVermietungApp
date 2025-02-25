package com.schneider.spring.springboot.autovermietungapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity representing a car rental transaction.
 * <p>
 * This entity stores rental details such as start and end date, total cost, and the associated user and car.
 */
@Getter
@Setter
@Entity
@Table(name = "rentals")
@Schema(description = "Represents a car rental transaction")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    @Schema(description = "Unique identifier for the rental", example = "1")
    private int id;

    @Column(name = "start_date")
    @Schema(description = "The start date of the rental period", example = "2025-02-01")
    private LocalDate startDate;

    @Column(name = "end_date")
    @Schema(description = "The end date of the rental period", example = "2025-02-07")
    private LocalDate endDate;

    @Column(name = "total_cost")
    @Schema(description = "The total cost of the rental", example = "700.00")
    private BigDecimal totalCost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Schema(description = "The user associated with the rental")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    @Schema(description = "The car associated with the rental")
    private Car car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return id == rental.id && Objects.equals(startDate, rental.startDate) && Objects.equals(endDate, rental.endDate) && Objects.equals(totalCost, rental.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, totalCost);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalCost=" + totalCost +
                '}';
    }
}
