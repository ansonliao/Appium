package com.maaii.automation.exception;

/**
 * Created by ansonliao on 30/8/15.
 */
public class NoSuchLocatorExistException extends ExceptionBase{
    public NoSuchLocatorExistException(String reason) {
        super(reason);
    }

    public NoSuchLocatorExistException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
