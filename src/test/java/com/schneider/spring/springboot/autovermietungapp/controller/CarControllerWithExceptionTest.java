package com.schneider.spring.springboot.autovermietungapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/db/schema-test.sql", "/db/data-test.sql"})
@WithMockUser(value = "ADMIN", password = "qqq", roles = {"USER", "ADMIN"})
class CarControllerWithExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarService carService;

    @MockitoBean
    private CarRepository carRepository;

    @Test
    void getAllCarsWithExceptionTest() throws Exception {
        when(carRepository.findAll()).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, () -> carService.getAllCars());
        Mockito.verify(carRepository, Mockito.times(2)).findAll();
    }

    @Test
    void getAllCarsByBrandWithExceptionTest() throws Exception {
        when(carRepository.findByBrand(Brand.VW)).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/getByBrand/VW")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, () -> carService.getCarsByBrand(String.valueOf(Brand.VW)));
        Mockito.verify(carRepository, Mockito.times(2)).findByBrand(Brand.VW);
    }

    @Test
    void getAllCarsByModelWithExceptionTest() throws Exception {
        when(carRepository.findCarsByModel(anyString())).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/getByModel/Golf")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, () -> carService.getCarsByModel("Golf"));
        Mockito.verify(carRepository, Mockito.times(2)).findCarsByModel("Golf");
    }

    @Test
    void deleteCarByIdNotFound() throws Exception {
        Integer notExistingId = 999;
        when(carRepository.findCarById(notExistingId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/cars/delete/{id}", notExistingId))
                .andExpect(status().isBadRequest());

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, () -> carService.deleteCarById(notExistingId));
        Mockito.verify(carRepository, Mockito.times(2)).findCarById(notExistingId);
    }

    @Test
    void deleteNonExistingCarThrowsException() {

        Integer nonExistingId = 999;
        when(carRepository.findCarById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, () -> carService.deleteCarById(nonExistingId));
    }
}