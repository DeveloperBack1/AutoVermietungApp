package com.schneider.spring.springboot.autovermietungapp.exception.errormessages;

/**
 * This class holds constant error messages used across the application.
 * <p>
 * These error messages are used to communicate error states to the user in a consistent manner.
 */
public class ErrorMessage {

    public static final String CARS_NOT_EXIST_IN_DATABASE
            = "THERE_ARE_NOT_ANY_CARS_IN_DATABASE";

    public static final String USER_NOT_EXIST = "EMAIL_DOESNT_EXIST_IN_DATABASE";

    public static final String INVALID_BRAND_NAME
            = "INVALID_BRAND_NAME";

    private ErrorMessage() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}