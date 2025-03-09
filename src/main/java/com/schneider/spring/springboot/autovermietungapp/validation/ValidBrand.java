package com.schneider.spring.springboot.autovermietungapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation for validating the correctness of a car brand.
 */
@Documented
@Constraint(validatedBy = BrandValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBrand {

    /**
     * Error message when the brand is invalid.
     */
    String message() default "Invalid brand name.";

    /**
     * Validation groups (empty by default).
     */
    Class<?>[] groups() default {};

    /**
     * Payload for additional metadata.
     */
    Class<? extends Payload>[] payload() default {};
}