

package com.schneider.spring.springboot.autovermietungapp.controller;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/db/schema.sql")
@Sql("/db/data.sql")
@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    private  ObjectMapper objectMapper;

    @Autowired
    private CarService carService;

    @MockitoBean
    public CarRepository carRepository;


    private static final Random RANDOM = new Random();

    @Test
    void getAllCarsPositiveTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String mockMvcResultAsString = result.getResponse().getContentAsString();

        System.out.println(objectMapper.readValue(mockMvcResultAsString, String.class) );

//        List<CarDTO> actualList = objectMapper.readValue(mockMvcResultAsString, new TypeReference<>() {});
//        List<CarDTO> expectedList = DtoCreator.getExpectedCarDtoList();
//
//        CarDTO actualListCarDTO = actualList.get(RANDOM.nextInt(actualList.size()));
//        String actualBrand = actualListCarDTO.getBrand();
//
//        boolean hasParameterInExpectedList = false;
//
//        for (CarDTO expectedCarDto : expectedList) {
//            String expectedBrand = expectedCarDto.getBrand();
//
//            if (expectedBrand.equals(actualBrand)) {
//                hasParameterInExpectedList = true;
//                break;
//            }
//        }
//
//        Assertions.assertEquals(expectedList.size(), actualList.size());
//        Assertions.assertTrue(hasParameterInExpectedList);
    }

    @Test
    void getAllCarsWithExceptionTest() throws Exception {
        when(carRepository.findAll()).thenReturn(List.of()); // Возвращаем пустой список

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cars/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Response: " + responseBody);

        Assertions.assertThrows(CarsNotExistInDataBaseException.class, ()-> carService.getAllCars());
    }

  }