package com.maaii.automation.selenium;

import com.maaii.automation.commons.Variables;
import com.maaii.automation.exception.ConfigurationException;
import com.maaii.automation.file.YamlParser;
import com.maaii.automation.utils.ConfigReader;
import org.apache.commons.validator.Var;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ansonliao on 3/8/15.
 */
public class WebDriverManager {

//    private static WebDriver driver;
    private static Map<Long, WebDriver> webDriverMap = new HashMap<Long, WebDriver>();
    private static String testType;
    private static String browser;
    private static String urlLink;
    private static int mxRetryCount;
    private static int waitTime;

    private static final String BROWSER1 = "firefox";
    private static final String BROWSER2 = "chrome";
    private static final String BROWSER3 = "ie";

    static {
        ConfigReader config = ConfigReader.getInstance();
        testType = config.getTestType();
        Variables.TEST_TYPE = testType;
        urlLink = config.getUrl();
        browser = config.getBrowser();
        mxRetryCount = config.getRetryCount();
        waitTime = config.getWaitTime();
        Variables.DEFAULT_WAIT_TIME_IN_MILLIS = waitTime;
    }

    public static WebDriver getInstance() {
        Long threadID = Thread.currentThread().getId();

        WebDriver driver = null;
        if (!webDriverMap.containsKey(threadID)) {
            if (browser.toLowerCase().trim().equals(BROWSER1)) {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            if (browser.toLowerCase().trim().equals(BROWSER2)) {
                // TODO add chrome handle code here
            }
            if (browser.toLowerCase().trim().equals(BROWSER3)) {
                // TODO add ie handle code here
            }

            webDriverMap.put(threadID, driver);
        }

        return webDriverMap.get(threadID);
    }

    public static void closeDriver() {
        Long threadID = Thread.currentThread().getId();

        webDriverMap.get(threadID).close();
    }

    public static void quitDriver() {
        Long threadID = Thread.currentThread().getId();

        webDriverMap.get(threadID).quit();
    }
}
