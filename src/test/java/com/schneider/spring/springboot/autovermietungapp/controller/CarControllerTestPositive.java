

package com.schneider.spring.springboot.autovermietungapp.controller;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.exception.CarsNotExistInDataBaseException;
import com.schneider.spring.springboot.autovermietungapp.repository.CarRepository;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/db/schema-test.sql", "/db/data-test.sql"})
class CarControllerTestPositive {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Random RANDOM = new Random();

    @Test
    void getAllCarsPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();

        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {
        });
        List<CarDTO> expectedList = DtoCreator.getExpectedCarDtoList();

        CarDTO actualListCarDTO = actualList.get(RANDOM.nextInt(actualList.size()));
        String actualBrand = actualListCarDTO.getBrand();

        boolean hasParameterInExpectedList = false;

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
        CarDTO carDTOTest = new CarDTO("TestModel","BMW","100");
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
    void getAllCarsByModelPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getByModel/Golf")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();
        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {});

        CarDTO expectedData = new CarDTO("Golf", "VW", "55.00");
        CarDTO actualData = actualList.get(0);

        Assertions.assertTrue(actualList.size() == 1);
        Assertions.assertTrue(expectedData.getModel().equals(actualData.getModel()));
        Assertions.assertTrue(expectedData.getBrand().equals(actualData.getBrand()));
        Assertions.assertTrue(expectedData.getPricePerDay().equals(actualData.getPricePerDay()));
    }
}