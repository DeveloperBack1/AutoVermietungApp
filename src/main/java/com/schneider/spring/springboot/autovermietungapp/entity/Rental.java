package com.schneider.spring.springboot.autovermietungapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity representing a rental transaction in the Autovermietung system.
 * It stores the details of the rental, including the car, user, rental period, and total cost.
 */
@Getter
@Setter
@Entity
@Table(name = "rentals")
@Schema(description = "Entity representing a rental transaction in the Autovermietung system.")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    @Schema(description = "Unique identifier for the rental transaction", example = "1", required = true)
    private int id;

    @Column(name = "start_date")
    @Schema(description = "Start date of the rental period", example = "2025-02-01", required = true)
    private LocalDate startDate;

    @Column(name = "end_date")
    @Schema(description = "End date of the rental period", example = "2025-02-07", required = true)
    private LocalDate endDate;

    @Column(name = "total_cost")
    @Schema(description = "Total cost of the rental transaction", example = "1399.99", required = true)
    private BigDecimal totalCost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Schema(description = "User associated with the rental", required = true)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    @Schema(description = "Car associated with the rental", required = true)
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
