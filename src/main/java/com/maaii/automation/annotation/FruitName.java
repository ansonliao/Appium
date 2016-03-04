package com.maaii.automation.annotation;

import java.lang.annotation.*;

/**
 * Created by ansonliao on 7/2/2016.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
