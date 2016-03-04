package com.maaii.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by cpuser on 4/8/15.
 */
public interface BasePage {
    void click(WebElement element);
    void clickAndSleep(WebElement element, int sleepTime);
    void type(WebElement element, String text);
    void typeByAppend(WebElement element, String text);
    void clearAndType(WebElement element);
    void getElement(String key);
    void getElementNotWait(String key);
    void getLocator(String key, boolean wait);
    void getBy(String key, String value);
    void verifyElementExist(By by);
    void verifyElementExist(String key);
}
