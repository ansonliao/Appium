package com.maaii.automation.selenium;

import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * Created by cpuser on 17/9/15.
 */
public class test implements BaseWebDriverManager {
    WebDriver driver;

    public WebDriver setUp() {
        return driver;
    }

    public WebDriver getInstance() {
        return driver;
    }

    public Map getConfig(String file) {
        return null;
    }
}
