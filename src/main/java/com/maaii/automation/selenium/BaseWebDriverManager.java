package com.maaii.automation.selenium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpuser on 17/9/15.
 */
public interface BaseWebDriverManager {
    Object setUp();
    Object getInstance();
    Map getConfig(String file);
}
