package com.maaii.automation.exception;

/**
 * Created by ansonliao on 31/7/15.
 */
public class ExceptionBase extends Exception {
    public ExceptionBase(String message) {
        super(
                "\n ----> " + message + " <----\n"
        );
    }

    public ExceptionBase(String message, Throwable cause) {
        super(message, cause);
    }
}
