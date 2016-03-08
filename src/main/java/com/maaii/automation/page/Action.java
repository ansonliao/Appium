package com.maaii.automation.page;

import com.maaii.automation.android.AndroidDriverManager;
import com.maaii.automation.commons.SWIPEDIRECTION;
import com.maaii.automation.commons.TestPlatform;
import com.maaii.automation.commons.Variables;
import com.maaii.automation.ios.IOSDriverManager;
import com.maaii.automation.selenium.ExtendWebElement;
import com.maaii.automation.utils.Utils;
import com.maaii.automation.utils.extentreport.ExtentTestUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.poi.hpsf.Util;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

/**
 * Created by ansonliao on 5/8/15.
 */
public class Action {

    public synchronized static void type(ExtendWebElement extendWebElement, String text) {
        extendWebElement.getElement().sendKeys(text);

        ExtentTestUtil.LogInfo(
                Utils.toBold("Type: ") +
                Utils.withPre(text) +
                Utils.toBold("Locator: ") +
                Utils.withPre(extendWebElement.getDesc()));
    }

    public synchronized static void clearAndType(ExtendWebElement extendWebElement, String text) {
        extendWebElement.getElement().clear();
        extendWebElement.getElement().sendKeys(text);

//        String logMsg = "<span style='font-weight:bold;'>Type: </span>" +
//                "<pre>" + text + "</pre>, " + "<span style='font-weight:bold;'>Locator: </span><pre>" +
//                extendWebElement.getDesc() + "</pre>";

        ExtentTestUtil.LogInfo(Utils.toBold("Type: ") +
                Utils.withPre(text) +
                Utils.toBold("Locator: ") +
                Utils.withPre(extendWebElement.getDesc())

        );
//        ExtentTestUtil.LogInfo("Type: " + text + ", " + extendWebElement.getDesc());
    }

    public static synchronized void typeByAppend(ExtendWebElement extendWebElement, String text) {
        String oldValueString = extendWebElement.getElement().getText();
        String newValueString = oldValueString + text;
        extendWebElement.getElement().sendKeys(newValueString);

//        String logMsg = "<span style='font-weight:bold;'>Type by Append: </span><pre>" +
//                newValueString + "</pre>, " + "<span style='font-weight:bold;'>Locator: </span><pre>" +
//                extendWebElement.getDesc() + "</pre>";
        ExtentTestUtil.LogInfo(
                Utils.toBold("Type by Append: ") +
                        Utils.withPre(newValueString) +
                        Utils.toBold("Locator: ") +
                        Utils.withPre(extendWebElement.getDesc())
                    );
//        ExtentTestUtil.LogInfo("Type: " + newValueString + ", " + extendWebElement.getDesc());
    }

    public static synchronized void click(ExtendWebElement extendWebElement) {
        extendWebElement.getElement().click();

//        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ", Click Action.");
//        String logMsg = "<span style='font-weight:bold;'>Click: </span><pre>" +
//                 extendWebElement + "</pre>";
        ExtentTestUtil.LogInfo(Utils.toBold("Click: ") + Utils.withPre(extendWebElement.getDesc()));
    }

    /**
     *
     * @param extendWebElement
     * @param sleepTime in ms
     * @throws InterruptedException
     */
    public static synchronized void clickAndWait(ExtendWebElement extendWebElement, int sleepTime) throws InterruptedException {
        extendWebElement.getElement().click();

//        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ", Click and Wait " + sleepTime + "ms");
//        ExtentTestUtil.LogInfo("ClickAndWait: " + extendWebElement.getDesc() + ", " + sleepTime + "ms");
        ExtentTestUtil.LogInfo(
                Utils.toBold("Click And Wait: ") +
                        Utils.withPre(sleepTime + "ms") +
                        Utils.toBold("Loator: ") +
                        Utils.withPre(extendWebElement.getDesc())
        );
        Thread.sleep(sleepTime);
    }

    public static synchronized void clear(ExtendWebElement extendWebElement) {
        extendWebElement.getElement().clear();

//        ExtentTestUtil.LogInfo(extendWebElement.getDesc() + ", Clear text field action");
//        ExtentTestUtil.LogInfo("Clear: " + extendWebElement.getDesc());
        ExtentTestUtil.LogInfo(
                Utils.toBold("Clear: ") +
                        Utils.withPre(extendWebElement.getDesc())
        );
    }

    public static synchronized void swipeDirection(SWIPEDIRECTION direction) throws InterruptedException {
        int startX, startY, endX, endY;
        int duraction = 1000;
        String logMsg = null;

        AppiumDriver driver = null;

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
                startY = (screenSize.height/10)*9;
                endY = (screenSize.height/10)*1;

                driver.swipe(startX, startY, endX, endY, duraction);
                logMsg = Utils.toBold("SWIPE UP: ") +
                        Utils.withPre(
                                "startX: " + startX + ", startY: " + startY +
                                ", endX: " + endX + ", endY: " + endY +
                                ", duration:" + duraction + "ms");
                ExtentTestUtil.LogInfo(logMsg);
                break;
            case DOWN:
                startX = screenSize.width/2;
                endX = startX;
                startY = (screenSize.height/10)*1;
                endY = (screenSize.height/10)*9;
                driver.swipe(startX, startY, endX, endY, duraction);
                logMsg = Utils.toBold("SWIPE DOWN: ") +
                        Utils.withPre(
                                "startX: " + startX + ", startY: " + startY +
                                ", endX: " + endX + ", endY: " + endY +
                                ", duration: " + duraction + "ms"
                        );
                ExtentTestUtil.LogInfo(logMsg);
                break;
            case LEFT:
                startX = (screenSize.width/10)*1;
                endX = (screenSize.width/10)*9;
                startY = screenSize.height/2;
                endY = startY;
                driver.swipe(startX, startY, endX, endY, duraction);
                logMsg = Utils.toBold("SWIPE LEFT: ") +
                        Utils.withPre(
                                "startX: " + startX + ", startY: " + startY +
                                ", endX: " + endX + ", endY: " + endY + ", duration: " + duraction + "ms"
                        );
                ExtentTestUtil.LogInfo(logMsg);
                break;
            default:
                startX = (screenSize.width/10)*9;
                endX = (screenSize.width/10)*1;
                startY = screenSize.height/2;
                endY = startY;
                driver.swipe(startX, startY, endX, endY, duraction);
                logMsg = Utils.toBold("SWIPE RIGHT: ") +
                        Utils.withPre(
                                "startX: " + startX + ", startY: " + startY +
                                ", endX: " + endX + ", endY: " + endY + ", duration: " + duraction + "ms"
                        );
                ExtentTestUtil.LogInfo(logMsg);
                break;

        }

        Thread.sleep(300);
    }

    private static synchronized Dimension getScreenSize(WebDriver driver) {
        return driver.manage().window().getSize();
    }

}
