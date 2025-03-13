package com.schneider.spring.springboot.autovermietungapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for Rental entity.
 * <p>
 * This class is used for transferring rental details between the client and the server.
 */
@Getter
@Setter
public class RentalDTO {

    @JsonProperty("rental_id")
    private int id;

    @JsonProperty("car_id")
    private int carId;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("total_cost")
    private BigDecimal totalCost;

    @JsonProperty("car_model")
    private String carModel;

    @JsonProperty("car_brand")
    private String carBrand;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_email")
    private String userEmail;

    public RentalDTO(int id, int carId, LocalDate startDate, LocalDate endDate,
                     BigDecimal totalCost, String carModel, String carBrand, String userName, String userEmail) {
        this.id = id;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.carModel = carModel;
        this.carBrand = carBrand;
        this.userName = userName;
        this.userEmail = userEmail;
    }



    public RentalDTO() {
    }

    @Override
    public String toString() {
        return "RentalDTO{" +
                "id=" + id +
                ", carId=" + carId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalCost=" + totalCost +
                ", carModel='" + carModel + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}