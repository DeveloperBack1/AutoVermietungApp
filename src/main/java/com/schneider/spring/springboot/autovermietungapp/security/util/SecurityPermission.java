package com.schneider.spring.springboot.autovermietungapp.security.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityPermission {
    public static final String[] SWAGGER_AND_LOGIN_PERMISSIONS = {"/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/auth/login"};
    public static final String[] GET_BY_BRAND_PERMISSIONS = {"ROLE_USER", "READ_PRIVILEGES"};
}
