package com.schneider.spring.springboot.autovermietungapp.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing car brands.
 * <p>
 * This enum defines a list of car brands used in the application.
 */
@Schema(description = "Car brands available in the system")
public enum Brand {

    @Schema(description = "BMW brand")
    BMW,

    @Schema(description = "Opel brand")
    OPEL,

    @Schema(description = "Audi brand")
    AUDI,

    @Schema(description = "Tesla brand")
    TESLA,

    @Schema(description = "Honda brand")
    HONDA,

    @Schema(description = "Toyota brand")
    TOYOTA,

    @Schema(description = "Volkswagen (VW) brand")
    VW,

    @Schema(description = "Ford brand")
    FORD
}
