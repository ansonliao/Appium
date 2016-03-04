package com.maaii.automation.annotation;

import java.lang.annotation.*;

/**
 * Created by ansonliao on 7/2/2016.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    public enum Color {
        BLUE, RED, GREEN
    };

    Color fruitColor() default Color.GREEN;
}
