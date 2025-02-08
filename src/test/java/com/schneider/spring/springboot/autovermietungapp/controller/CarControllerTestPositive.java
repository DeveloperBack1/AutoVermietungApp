

package com.schneider.spring.springboot.autovermietungapp.controller;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;

import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schneider.spring.springboot.autovermietungapp.util.DtoCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/db/schema-test.sql", "/db/data-test.sql"})
class CarControllerTestPositive {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    public CarService carServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Random RANDOM = new Random();

    @Test
    void getAllCarsPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAllDTO")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();

        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {
        });
        List<CarDTO> expectedList = DtoCreator.getExpectedCarDtoList();

        CarDTO actualListCarDTO = actualList.get(RANDOM.nextInt(actualList.size()));
        String actualBrand = actualListCarDTO.getBrand();

        boolean hasParameterInExpectedList = true;

        for (CarDTO expectedCarDto : expectedList) {
            String expectedBrand = expectedCarDto.getBrand();

            if (expectedBrand.equals(actualBrand)) {
                hasParameterInExpectedList = true;
                break;
            }
        }
        Assertions.assertEquals(expectedList.size(), actualList.size());
        Assertions.assertTrue(hasParameterInExpectedList);
    }

    @Test
    void createCarTest() throws Exception {
        CarDTO carDTOTest = new CarDTO("TestModel", "BMW", "100");
        String jsonString = objectMapper.writeValueAsString(carDTOTest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/cars/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();
        Car carResult = objectMapper.readValue(mockMvcResultAsString, Car.class);

        String expectedBrand = carDTOTest.getBrand();
        String actualBrand = String.valueOf(carResult.getBrand());

        Assertions.assertEquals(expectedBrand, actualBrand);
    }

    @Test
    void getCarByModel() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getCarByModel")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        CarDTO carDTOTest1 = new CarDTO("TestModel1", "BMW3", "100");
        CarDTO carDTOTest2 = new CarDTO("TestModel1", "BMW5", "110");
        CarDTO carDTOTest3 = new CarDTO("TestModel3", "BMW7", "110");

        String actualModel = carDTOTest1.getModel();
        String expectedModel = carDTOTest2.getModel();

        Assertions.assertEquals(actualModel, expectedModel);
        Assertions.assertEquals(carDTOTest1.getModel(), carDTOTest2.getModel());
        Assertions.assertNotEquals(carDTOTest1.getModel(), carDTOTest3.getModel());

    }


    @Test
    void deleteCarById() throws Exception {
        Integer carId = 2;

        doNothing().when(carServiceMock).deleteById(carId);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/cars/{id}", carId))
                .andExpect(status().isOk());


        verify(carServiceMock, times(1)).deleteById(carId);
    }
}