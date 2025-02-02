

package com.schneider.spring.springboot.autovermietungapp;

import com.schneider.spring.springboot.autovermietungapp.controller.CarController;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import com.schneider.spring.springboot.autovermietungapp.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    private List<CarDTO> mockCarList;

    @BeforeEach
    void setUp() {
        mockCarList = Arrays.asList(
                new CarDTO("Corolla", "Toyota", "Corolla"),
                new CarDTO("Civic", "Honda", "Civic")
        );
    }

    @Test
    void getAllCars_ShouldReturnCarList() {
        // Arrange
        when(carService.getAllCars()).thenReturn(mockCarList);

        // Act
        List<CarDTO> result = carController.getAllCars();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Toyota", result.get(0).getBrand());
        verify(carService, times(1)).getAllCars();
    }

    @Test
    void handleException_ShouldReturnBadRequestResponse() {
        // Arrange
        Exception exception = new Exception("Test error");

        // Act
        ResponseEntity<String> response = carController.handleException(exception);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Test error", response.getBody());
    }
}

