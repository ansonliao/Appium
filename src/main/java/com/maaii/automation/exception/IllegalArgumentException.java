package com.maaii.automation.exception;

/**
 * Created by ansonliao on 8/3/2016.
 */
public class IllegalArgumentException extends ExceptionBase {
    public IllegalArgumentException(String reason) {
        super(reason);
    }

    public IllegalArgumentException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
