package com.maaii.automation.exception;

/**
 * Created by ansonliao on 31/7/15.
 */
public class ConfigurationException extends ExceptionBase{
    public ConfigurationException(String reason) {
        super(reason);
    }

    public ConfigurationException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
