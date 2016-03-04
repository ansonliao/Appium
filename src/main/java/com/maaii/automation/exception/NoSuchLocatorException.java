package com.maaii.automation.exception;

/**
 * Created by ansonliao on 3/8/15.
 */
public class NoSuchLocatorException extends ExceptionBase {
    public NoSuchLocatorException(String reason) {
        super(reason);
    }

    public NoSuchLocatorException(String reason, Throwable cause) {
        super(reason, cause);
    }

}