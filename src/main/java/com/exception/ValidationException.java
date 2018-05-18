package com.exception;

import java.util.Map;

public class ValidationException extends Exception {
    private Map<String, String> violationsMap;

    public ValidationException(String message, Map<String, String> violationsMap) {
        super(message);
        this.violationsMap = violationsMap;
    }

    public Map<String, String> getViolationsMap() {
        return violationsMap;
    }

}
