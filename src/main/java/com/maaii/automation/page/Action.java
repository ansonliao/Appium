package com.maaii.automation.page;

import com.maaii.automation.android.AndroidDriverManager;
import com.maaii.automation.commons.SWIPEDIRECTION;
import com.maaii.automation.commons.TestPlatform;
import com.maaii.automation.commons.Variables;
import com.maaii.automation.ios.IOSDriverManager;
import com.maaii.automation.selenium.ExtendWebElement;
import com.maaii.automation.utils.extentreport.ExtentTestUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

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

    public static synchronized void typeByAppend(ExtendWebElement extendWebElement, String text) {
        String oldValueString = extendWebElement.getElement().getText();
        String newValueString = oldValueString + text;
        extendWebElement.getElement().sendKeys(newValueString);

        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ": TypeByAppend, after typed, the text field is [" + newValueString + "].");
    }

    public static synchronized void click(ExtendWebElement extendWebElement) {
        extendWebElement.getElement().click();
        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ": Click action.");
    }

    public static synchronized void clickAndWait(ExtendWebElement extendWebElement, int sleepTime) throws InterruptedException {
        extendWebElement.getElement().click();
        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ": ClickAndWait [wait " + sleepTime + " ms]." );
        Thread.sleep(sleepTime);
    }

    public static synchronized void clear(ExtendWebElement extendWebElement) {
        extendWebElement.getElement().clear();
        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ": Clear text field.");
    }

    public static synchronized void swipeDirection(SWIPEDIRECTION direction) throws InterruptedException {
        int startX, startY, endX, endY;
        int duraction = 2000;
        String logMsg = null;

        AppiumDriver driver = null;

//        if (Variables.TEST_TYPE.equalsIgnoreCase(TestPlatform.ANDROID.toString())) {
//            driver = (AndroidDriver) AndroidDriverManager.getInstance();
//        }
//
//        if (Variables.TEST_TYPE.equalsIgnoreCase(TestPlatform.IOS.toString())) {
//            driver =(IOSDriver) IOSDriverManager.getInstance();
//        }

        switch (Variables.TEST_TYPE) {
            case WEB:
                /**
                 * TODO: Add web handle code here
                 */
                break;
            case IOS:
                driver =(IOSDriver) IOSDriverManager.getInstance();
                break;
            default:
                driver = (AndroidDriver) AndroidDriverManager.getInstance();
                break;
        }

        Dimension screenSize = getScreenSize(driver);

        switch (direction) {
            case UP:
                startX = screenSize.width/2;
                endX = startX;
                startY = (screenSize.height/10)*8;
                endY = (screenSize.height/10)*2;

                driver.swipe(startX, startY, endX, endY, duraction);
                logMsg = "SWIPE UP: [startX: " + startX + ", startY: " + startY +
                        ", endX: " + endX + ", endY: " + endY + ", duration: 1000ms" + "] ";
                ExtentTestUtil.LogInfo(logMsg);
                System.out.println(logMsg);
                break;
            case DOWN:
                startX = screenSize.width/2;
                endX = startX;
                startY = (screenSize.height/10)*2;
                endY = (screenSize.height/10)*8;
                driver.swipe(startX, startY, endX, endY, duraction);
                logMsg = "SWIPE DOWN: [startX: " + startX + ", startY: " + startY +
                        ", endX: " + endX + ", endY: " + endY + ", duration: 1000ms" + "] ";
                ExtentTestUtil.LogInfo(logMsg);
                System.out.println(logMsg);
                break;
            case LEFT:
                startX = (screenSize.width/10)*2;
                endX = (screenSize.width/10)*8;
                startY = screenSize.height/2;
                endY = startY;
                driver.swipe(startX, startY, endX, endY, duraction);
                logMsg = "SWIPE LEFT: [startX: " + startX + ", startY: " + startY +
                        ", endX: " + endX + ", endY: " + endY + ", duration: 1000ms" + "] ";
                ExtentTestUtil.LogInfo(logMsg);
                System.out.println(logMsg);
                break;
            default:
                startX = (screenSize.width/10)*8;
                endX = (screenSize.width/10)*2;
                startY = screenSize.height/2;
                endY = startY;
                driver.swipe(startX, startY, endX, endY, duraction);
                logMsg = "SWIPE RIGHT: [startX: " + startX + ", startY: " + startY +
                        ", endX: " + endX + ", endY: " + endY + ", duration: 1000ms" + "] ";
                ExtentTestUtil.LogInfo(logMsg);
                System.out.println(logMsg);
                break;

        }

        Thread.sleep(300);
    }

    private static synchronized Dimension getScreenSize(WebDriver driver) {
        return driver.manage().window().getSize();
    }

}
