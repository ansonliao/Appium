package com.maaii.automation.page;

import com.maaii.automation.selenium.ExtendWebElement;
import com.maaii.automation.utils.extentreport.ExtentTestUtil;

/**
 * Created by ansonliao on 5/8/15.
 */
public class Action {

    public synchronized static void type(ExtendWebElement extendWebElement, String text) {
        extendWebElement.getElement().sendKeys(text);
        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ": Type [" + text + "].");
    }

    public synchronized static void clearAndType(ExtendWebElement extendWebElement, String text) {
        extendWebElement.getElement().clear();
        extendWebElement.getElement().sendKeys(text);
        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ": ClearAndType [" + text + "].");
    }

    public static void typeByAppend(ExtendWebElement extendWebElement, String text) {
        String oldValueString = extendWebElement.getElement().getText();
        String newValueString = oldValueString + text;
        extendWebElement.getElement().sendKeys(newValueString);

        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ": TypeByAppend, after typed, the text field is [" + newValueString + "].");
    }

    public static void click(ExtendWebElement extendWebElement) {
        extendWebElement.getElement().click();
        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ": Click action.");
    }

    public static void clickAndWait(ExtendWebElement extendWebElement, int sleepTime) throws InterruptedException {
        extendWebElement.getElement().click();
        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ": ClickAndWait [wait " + sleepTime + " ms]." );
        Thread.sleep(sleepTime);
    }

    public static void clear(ExtendWebElement extendWebElement) {
        extendWebElement.getElement().clear();
        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ": Clear text field.");
    }

}
