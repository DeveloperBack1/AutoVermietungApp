package com.schneider.spring.springboot.autovermietungapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.entity.Car;
import com.schneider.spring.springboot.autovermietungapp.util.DtoCreator;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"/db/schema-test.sql", "/db/data-test.sql"})
@WithMockUser(value = "ADMIN", password = "qqq", roles = {"USER", "ADMIN"})
class CarControllerTestPositive {

    private final CarDTO EXPECTED_DATA = new CarDTO("Golf", "VW", "55.00");

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
        String actualModel = actualListCarDTO.getModel();

        boolean hasParameterInExpectedList = false;

        for (CarDTO expectedCarDto : expectedList) {
            String expectedModel = expectedCarDto.getModel();

            if (expectedModel.equals(actualModel)) {
                hasParameterInExpectedList = true;
                break;
            }
        }
        Assertions.assertEquals(expectedList.size(), actualList.size());
        Assertions.assertTrue(hasParameterInExpectedList);
    }

    @Test
    void getAllCarsByBrandPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getByBrand/VW")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();
        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {
        });

        CarDTO actualData = actualList.get(0);

        Assertions.assertEquals(1, actualList.size());
        Assertions.assertEquals(EXPECTED_DATA.getModel(), actualData.getModel());
        Assertions.assertEquals(EXPECTED_DATA.getBrand(), actualData.getBrand());
        Assertions.assertEquals(EXPECTED_DATA.getPricePerDay(), actualData.getPricePerDay());
    }

    @Test
    void getAllCarsByModelPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getByModel/Golf")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();
        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {
        });

        CarDTO actualData = actualList.get(0);

        Assertions.assertEquals(1, actualList.size());
        Assertions.assertEquals(EXPECTED_DATA.getModel(), actualData.getModel());
        Assertions.assertEquals(EXPECTED_DATA.getBrand(), actualData.getBrand());
        Assertions.assertEquals(EXPECTED_DATA.getPricePerDay(), actualData.getPricePerDay());
    }

    @Test
    void createCarTest() throws Exception {
        CarDTO carDTOTest = DtoCreator.createExpectedCarDto();
        String jsonString = objectMapper.writeValueAsString(carDTOTest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/cars/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();
        Car createdCar = objectMapper.readValue(mockMvcResultAsString, Car.class);

        String expectedBrand = carDTOTest.getBrand();
        String actualBrand = String.valueOf(createdCar.getBrand());

        Assertions.assertEquals(expectedBrand, actualBrand);

        mockMvc.perform(MockMvcRequestBuilders.delete("/cars/delete/{id}", createdCar.getId()))
                .andExpect(status().isOk());
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

    @Test
    void updateCarTest() throws Exception {

        CarDTO carDTOTest = new CarDTO("Golf", "VW", "55.00");
        String jsonString = objectMapper.writeValueAsString(carDTOTest);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/cars/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andReturn();

        Car createdCar = objectMapper.readValue(createResult.getResponse().getContentAsString(), Car.class);
        Assertions.assertNotNull(createdCar);

        CarDTO updatedCarDTO = new CarDTO("Passat", "VW", "65.00");
        String updatedJsonString = objectMapper.writeValueAsString(updatedCarDTO);

        MvcResult updateResult = mockMvc.perform(MockMvcRequestBuilders.put("/cars/update/{id}", createdCar.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJsonString))
                .andExpect(status().isOk())
                .andReturn();

        Car updatedCar = objectMapper.readValue(updateResult.getResponse().getContentAsString(), Car.class);

        Assertions.assertEquals("Passat", updatedCar.getModel());
        Assertions.assertEquals("VW", updatedCar.getBrand().toString());
        Assertions.assertEquals(new BigDecimal("65.00"), updatedCar.getPricePerDay());
    }


}