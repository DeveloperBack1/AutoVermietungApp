package com.schneider.spring.springboot.autovermietungapp.security.utility;

import lombok.experimental.UtilityClass;

/**
 * Utility class that holds various security-related permission constants.
 * These constants represent the roles and permissions required for specific API endpoints.
 * This class is marked as a utility class, as it provides only static values and should not be instantiated.
 */

@UtilityClass
public class SecurityPermission {


    private final String ROLE_ADMIN = "ROLE_ADMIN";
    private final String ROLE_USER = "ROLE_USER";
    private  final String READ_PRIVILEGES = "READ_PRIVILEGES";
    private  final String WRITE_PRIVILEGES = "WRITE_PRIVILEGES";

    /**
     * Permissions for accessing Swagger documentation and login API endpoints.
     * These endpoints are typically public and don't require strict security enforcement.
     */


    private final String[] SWAGGER_AND_LOGIN_PERMISSIONS = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/auth/login"
    };

    /**
     * Permissions for accessing the car data by brand.
     * These permissions require the user to have either the 'ROLE_USER' or 'READ_PRIVILEGES' authority.
     */

    private final String[] GET_BY_BRAND_PERMISSIONS = {
            ROLE_USER,
            READ_PRIVILEGES
    };

    /**
     * Permissions for accessing the car data by model.
     * These permissions require the user to have either the 'ROLE_ADMIN' or 'READ_PRIVILEGES' authority.
     */

    private final String[] GET_BY_MODEL_PERMISSIONS = {
            ROLE_ADMIN,
            READ_PRIVILEGES
    };

    /**
     * Permissions for creating a new car entry.
     * These permissions require the user to have the 'ROLE_ADMIN' or 'WRITE_PRIVILEGES' authority.
     */

    private final String[] CARS_CREATE_PERMISSIONS = {
            ROLE_ADMIN,
            WRITE_PRIVILEGES
    };

    /**
     * Permissions for deleting a car entry.
     * These permissions require the user to have the 'ROLE_ADMIN' or 'WRITE_PRIVILEGES' authority.
     */

    private  final String[] CARS_DELETE_PERMISSIONS = {
            ROLE_ADMIN,
            WRITE_PRIVILEGES
    };

    public  String[] getSwaggerAndLoginPermissions() {
        return SWAGGER_AND_LOGIN_PERMISSIONS;
    }
    public  String[] getGetByBrandPermissions() {
        return GET_BY_BRAND_PERMISSIONS;
    }
    public  String[] getGetByModelPermissions() {
        return GET_BY_MODEL_PERMISSIONS;
    }
    public  String[] getCarsCreatePermissions() {
        return CARS_CREATE_PERMISSIONS;
    }
    public  String[] getCarsDeletePermissions() {
        return CARS_DELETE_PERMISSIONS;
    }
}