
package com.schneider.spring.springboot.autovermietungapp.controller;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.schneider.spring.springboot.autovermietungapp.util.DtoCreator;

import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/db/schema-test.sql", "/db/data-test.sql"})
@WithMockUser(value = "ADMIN", password = "qqq", roles = {"USER", "ADMIN"})
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

        boolean hasParameterInExpectedList=false ;

        for (CarDTO expectedCarDto : expectedList) {
            String expectedBrand = expectedCarDto.getBrand();

            if (expectedBrand.equals(actualBrand)) {
                hasParameterInExpectedList = true;
                break;
            }
        }
        assertEquals(expectedList.size(), actualList.size());
        Assertions.assertTrue(hasParameterInExpectedList);
    }

    @Test
    void getAllCarsByBrandPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getByBrand/VW")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();
        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {});

        CarDTO expectedData = new CarDTO("Golf", "VW", "55.00");
        CarDTO actualData = actualList.get(0);

        assertEquals(1, actualList.size());
        assertEquals(expectedData.getModel(), actualData.getModel());
        assertEquals(expectedData.getBrand(), actualData.getBrand());
        assertEquals(expectedData.getPricePerDay(), actualData.getPricePerDay());
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

        assertEquals(1, actualList.size());
        assertEquals(expectedData.getModel(), actualData.getModel());
        assertEquals(expectedData.getBrand(), actualData.getBrand());
        assertEquals(expectedData.getPricePerDay(), actualData.getPricePerDay());
    }

    @Test
    void CreateCarPositiveTest() throws Exception {
        CarDTO carDTO = new CarDTO(
                "535",
                "BMW",
                "100");

        String  jsonRequest = objectMapper.writeValueAsString(carDTO);

        MvcResult result = mockMvc.perform(post("/cars/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        Car carResponse = objectMapper.readValue(jsonResponse, Car.class);

        assertAll(
                () -> assertEquals(200, result.getResponse().getStatus()),
                () -> assertNotNull(carResponse),
                () -> assertNotNull(carResponse.getId())
        );
    }

    @Test
    void deleteCarTest() throws Exception {
        CarDTO carDTOTest = DtoCreator.createExpectedCarDto();
        String jsonString = objectMapper.writeValueAsString(carDTOTest);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/cars/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andReturn();

        Car createdCar = objectMapper.readValue(createResult.getResponse().getContentAsString(), Car.class);
        Assertions.assertNotNull(createdCar);

        mockMvc.perform(MockMvcRequestBuilders.delete("/cars/delete/{id}", createdCar.getId()))
                .andExpect(status().isOk());

        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get("/cars/{id}", createdCar.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(404, getResult.getResponse().getStatus());
    }



}