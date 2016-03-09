package com.maaii.automation.annotation;

import java.lang.annotation.*;

/**
 * Created by ansonliao on 9/3/2016.
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Locators {
    String value();
}
