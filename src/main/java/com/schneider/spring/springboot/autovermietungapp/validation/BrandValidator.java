package com.schneider.spring.springboot.autovermietungapp.validation;

import com.schneider.spring.springboot.autovermietungapp.entity.enums.Brand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Arrays;

/**
 * Validator for the {@link ValidBrand} annotation.
 * <p>
 * This class ensures that the provided brand exists in the {@link Brand} enum.
 * </p>
 */
@Schema(description = "Validator for checking if a car brand exists in the predefined list")
public class BrandValidator implements ConstraintValidator<ValidBrand, String> {

    @Override
    public boolean isValid(String brand, ConstraintValidatorContext context) {
        if (brand == null || brand.isBlank()) {
            return false;
        }

        return Arrays.stream(Brand.values())
                .anyMatch(b -> b.name().equalsIgnoreCase(brand));
    }
}