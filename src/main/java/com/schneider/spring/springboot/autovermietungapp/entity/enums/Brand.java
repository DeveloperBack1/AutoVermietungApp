package com.schneider.spring.springboot.autovermietungapp.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing car brands available in the Autovermietung system.
 */
@Schema(description = "Enumeration of car brands available in the Autovermietung system")
public enum Brand {

    @Schema(description = "BMW brand", example = "BMW")
    BMW,

    @Schema(description = "OPEL brand", example = "OPEL")
    OPEL,

    @Schema(description = "AUDI brand", example = "AUDI")
    AUDI,

    @Schema(description = "TESLA brand", example = "TESLA")
    TESLA,

    @Schema(description = "HONDA brand", example = "HONDA")
    HONDA,

    @Schema(description = "TOYOTA brand", example = "TOYOTA")
    TOYOTA,

    @Schema(description = "VW brand", example = "VW")
    VW,

    @Schema(description = "FORD brand", example = "FORD")
    FORD
}
