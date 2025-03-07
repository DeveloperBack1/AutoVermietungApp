package com.schneider.spring.springboot.autovermietungapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

/**
 * Implementation of the {@link IDChecker} annotation validator.
 * <p>
 * This class validates that a given ID (as a string) matches a specific pattern, ensuring it only contains alphanumeric characters.
 * </p>
 * <p>
 * The {@link IDCheckerImpl} is invoked when the {@link IDChecker} annotation is applied to a method parameter.
 * </p>
 */
public class IDCheckerImpl implements ConstraintValidator<IDChecker, String> {
    private static final String ID_PATTERN = "\\w+";

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation the annotation instance for the constraint
     */
    @Override
    public void initialize(IDChecker constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Validates that the given ID contains only alphanumeric characters.
     *
     * @param value   the value to be validated (typically a String)
     * @param context the validation context
     * @return true if the ID matches the required pattern, otherwise false
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(id -> id.matches(ID_PATTERN))
                .orElse(false);
    }
}