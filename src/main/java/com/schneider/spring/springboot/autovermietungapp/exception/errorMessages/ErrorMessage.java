package com.schneider.spring.springboot.autovermietungapp.exception.errorMessages;

/**
 * This class holds constant error messages used across the application.
 * <p>
 * These error messages are used to communicate error states to the user in a consistent manner.
 */
public class ErrorMessage {

    /**
     * Error message when no cars are found in the database.
     */
    public static final String CARS_NOT_EXIST_IN_DATABASE
            = "THERE_ARE_NOT_ANY_CARS_IN_DATABASE";

    /**
     * Error message when the user email does not exist in the database.
     */
    public static final String USER_NOT_EXIST = "EMAIL_DOESNT_EXIST_IN_DATABASE";

    /**
     * Error message for incorrect brand name.
     */
    public static final String INCORRECT_BRAND_NAME
            = "INCORRECT_BRAND_NAME";
}