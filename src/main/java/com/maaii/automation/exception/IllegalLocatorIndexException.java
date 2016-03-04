package com.maaii.automation.exception;

/**
 * Created by ansonliao on 12/9/15.
 */
public class IllegalLocatorIndexException extends ExceptionBase{
    public IllegalLocatorIndexException(String reason) {
        super(reason);
    }

    public IllegalLocatorIndexException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
