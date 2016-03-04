package com.maaii.automation.annotation;

import jdk.nashorn.internal.ir.ReturnNode;

import java.lang.annotation.*;

/**
 * Created by ansonliao on 7/2/2016.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
    public int id() default -1;
    public String name() default "";
    public String address() default "";

}
