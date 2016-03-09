package com.maaii.automation.annotation;

/**
 * Created by ansonliao on 7/2/2016.
 */

@Author(name = "Anson Liao", group = "Automation Team")
@Description(value = "Class level description annotation")
public class Utility {

    @Author(name = "AnsonLiao", group = "QA Team")
    @Description("Method level description annotation")
    public String work() {
        return "work over!";
    }
}
