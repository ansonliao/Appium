package com.maaii.automation.annotation;

/**
 * Created by ansonliao on 7/2/2016.
 */
public @interface TestInfo {
    TestPlatForm type() default TestPlatForm.WEB;
    public String url() default "";
    public String config() default "";
}
