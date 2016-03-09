package com.maaii.automation.annotation;

import java.lang.annotation.*;

/**
 * Created by ansonliao on 7/2/2016.
 * @author ansonliao
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Author {
    String name();
    String group();
}
