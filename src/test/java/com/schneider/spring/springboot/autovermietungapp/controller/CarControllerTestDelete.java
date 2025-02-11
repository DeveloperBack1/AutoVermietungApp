package com.schneider.spring.springboot.autovermietungapp.controller;

import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/db/schema-test.sql", "/db/data-test.sql"})
@ExtendWith(MockitoExtension.class)
public class CarControllerTestDelete {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarService carService;


    @Test
    void testDeleteCarById_Found() throws Exception {
        // Arrange
        Integer carId = 123;
        Car mockCar = new Car();
        mockCar.setId(carId);
        mockCar.setModel("X5");
        mockCar.setBrand(Brand.BMW);
        mockCar.setPricePerDay(BigDecimal.valueOf(100));

        when(carService.deleteCarById(carId)).thenReturn(mockCar);

        // Act & Assert
        mockMvc.perform(delete("/cars/{id}", carId))
                .andExpect(status().isNoContent()) // Ожидаем 204 No Content
                .andExpect(content().string("")); // Проверяем, что тело пустое

        // Verify
        verify(carService, times(1)).deleteCarById(carId);
    }


    @Test
    void testDeleteCarById_NotFound() throws Exception {
        // Arrange
        Integer carId = 999;
        when(carService.deleteCarById(carId)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(delete("/cars/{id}", carId))
                .andExpect(status().isNotFound());

        // Verify
        verify(carService, times(1)).deleteCarById(carId);
    }
}