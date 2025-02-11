package com.schneider.spring.springboot.autovermietungapp.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class IDCheckerImpl implements ConstraintValidator<IDChecker, String> {
    private static final String idPattern = "\\w+";

    @Override
    public void initialize(IDChecker constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .filter(id -> id.isBlank())
                .map(id -> id.matches(idPattern))
                .orElse(false);
    }
}

