package com.maaii.automation.android;

import com.maaii.automation.selenium.appium.AppiumServer;
import com.maaii.automation.utils.ConfigReader;
import com.maaii.automation.utils.Utils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ansonliao on 3/3/2016.
 */
public class AndroidDriverManager {
    private static Map<Long, AndroidDriver> androidDriverMap = new HashMap<Long, AndroidDriver>();
    private static Map<Long, Map<String, String>> configMap = new HashMap<Long, Map<String, String>>();
    private static Map<Long, String> appMap = new HashMap<Long, String>();
    protected static Map<Long, String> locatorYamlMap = new HashMap<Long, String>();
    private static File app;
    private static File appDir;
    private static String deviceName;
    private static int mxRetryCount;
    private static int waitTime;

//    static {
//        ConfigReader config = ConfigReader.getInstance();
//        appDir = new File(config.getAppDir());
//        app = new File(appDir, config.getApp());
//        deviceName = config.getDeviceName();
//        mxRetryCount = config.getRetryCount();
//        waitTime = config.getWaitTime();
//        Variables.DEFAULT_WAIT_TIME_IN_MILLIS = waitTime;
//    }

    public static synchronized AndroidDriver getInstance() {
        Long threadID = Utils.getThreadID();

        if (!androidDriverMap.containsKey(threadID)) {
            ConfigReader cr = ConfigReader.getInstance();

            if (cr.getLocatorYamlFile() != null) {
                locatorYamlMap.put(threadID, cr.getLocatorYamlFile());
            }

            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
//            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
//            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, cr.getDeviceName());
            capabilities.setCapability(MobileCapabilityType.APP, (new File(cr.getAppDir(), cr.getApp())).getAbsolutePath());
//            capabilities.setCapability(MobileCapabilityType);
            AndroidDriver driver = new AndroidDriver<WebElement>(AppiumServer.gerService().getUrl(), capabilities);

            androidDriverMap.put(threadID, driver);
        }

        return androidDriverMap.get(threadID);
    }

    public static synchronized void closeDriver() {
        androidDriverMap.get(Utils.getThreadID()).close();
    }

    public static synchronized void quitDriver() {
        androidDriverMap.get(Utils.getThreadID()).quit();
        AppiumServer.gerService().stop();
    }

    public static synchronized String getLocatorYaml() {
        Long threadID = Utils.getThreadID();

        if (locatorYamlMap.containsKey(threadID)) {
            return locatorYamlMap.get(threadID);
        }
        else
            return null;
    }

}
