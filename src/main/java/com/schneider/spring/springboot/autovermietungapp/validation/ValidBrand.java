package com.schneider.spring.springboot.autovermietungapp.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation for validating the correctness of a car brand.
 * <p>
 * This annotation ensures that the provided brand name is valid according to {@link BrandValidator}.
 * </p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>
 * public ResponseEntity&lt;Car&gt; getCarByBrand(@ValidBrand String brand) {
 *     // method implementation
 * }
 * </pre>
 */
@Documented
@Constraint(validatedBy = BrandValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Schema(description = "Validation annotation to check if the provided brand name is valid.")
public @interface ValidBrand {

    /**
     * Error message when the brand is invalid.
     *
     * @return default error message
     */
    @Schema(description = "Error message returned if the brand name is invalid.", example = "Invalid brand name.")
    String message() default "INVALID_BRAND_NAME";

    /**
     * Validation groups (empty by default).
     *
     * @return validation groups
     */
    @Schema(hidden = true)
    Class<?>[] groups() default {};

    /**
     * Payload for additional metadata.
     *
     * @return payload classes
     */
    @Schema(hidden = true)
    Class<? extends Payload>[] payload() default {};
}
