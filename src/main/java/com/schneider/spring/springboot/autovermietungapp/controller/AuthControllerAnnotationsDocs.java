package com.schneider.spring.springboot.autovermietungapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface AuthControllerAnnotationsDocs {

    @Operation(summary = "User Login", description = "Authenticates a user with the provided credentials and returns a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated and JWT token generated",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request: invalid login credentials",
                    content = @Content(mediaType = "application/json"))
    })
    @interface UserLoginSwagger {
    }
}

