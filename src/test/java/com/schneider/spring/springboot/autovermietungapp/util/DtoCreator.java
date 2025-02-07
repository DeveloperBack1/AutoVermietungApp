package com.schneider.spring.springboot.autovermietungapp.util;
import com.schneider.spring.springboot.autovermietungapp.dto.CarDTO;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class DtoCreator {

    public static List<CarDTO> getExpectedCarDtoList() {
        return Arrays.asList(new CarDTO("X5", "BMW", "100.00"),
                new CarDTO("Corsa", "OPEL", "50.00"),
                new CarDTO("A4", "AUDI", "80.00"),
                new CarDTO("Model 3", "TESLA", "120.00"),
                new CarDTO("Golf", "VW", "55.00"));
    }
}