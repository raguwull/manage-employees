package com.employee_application.manage_employees.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionMessage {
    @JsonProperty
    private String message;
    
    @JsonProperty
    private boolean success;

    public ExceptionMessage() {}

    public ExceptionMessage(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // Getters and Setters
}
