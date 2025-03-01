package com.schneider.spring.springboot.autovermietungapp.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to validate that a parameter (typically an ID) is numeric.
 * <p>
 * This annotation can be applied to method parameters to enforce that they contain only numeric values.
 * It is used in conjunction with a custom validator (IDCheckerImpl) that checks the validity of the ID.
 * </p>
 *
 * <p>By default, the validation message is: "ID is not valid. Must contains only NUMERIC parameter".</p>
 *
 * @see IDCheckerImpl
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IDCheckerImpl.class)
public @interface IDChecker {

    /**
     * The default message to return when the validation fails.
     *
     * @return the default error message
     */
    String message() default "ID is not valid. Must contains only NUMERIC parameter";
}