package com.schneider.spring.springboot.autovermietungapp.controller;
import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import com.schneider.spring.springboot.autovermietungapp.exception.CarsNotExistInDataBaseException;
import com.schneider.spring.springboot.autovermietungapp.repository.CarRepository;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/db/schema-test.sql", "/db/data-test.sql"})
class CarControllerTestWithException {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarService carService;

    @MockitoBean
    public CarRepository carRepository;

    @Test
    void getAllCarsWithExceptionTest() throws Exception {
        when(carRepository.findAll()).thenReturn(List.of());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Response: " + responseBody);

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, () -> carService.getAllCars());
        Mockito.verify(carRepository, Mockito.times(2)).findAll();
    }

    @Test
    void getAllCarsByBrandWithExceptionTest() throws Exception {
        when(carRepository.findByBrand(Brand.VW)).thenReturn(List.of());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getByBrand/VW")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Response: " + responseBody);

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, () -> carService.getAllCars());
        Mockito.verify(carRepository, Mockito.times(1)).findByBrand(Brand.VW);
    }

    @Test
    void getAllCarsByModelWithExceptionTest() throws Exception {
        when(carRepository.findCarsByModel(anyString())).thenReturn(List.of());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getByBrand/VW")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Response: " + responseBody);

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, () -> carService.getAllCars());
        Mockito.verify(carRepository, Mockito.times(1)).findByBrand(Brand.VW);
    }

    @Test
    void deleteCarByIdNotFound() throws Exception {

        Integer carId = 2;

        mockMvc.perform(delete("/cars/{id}", carId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}