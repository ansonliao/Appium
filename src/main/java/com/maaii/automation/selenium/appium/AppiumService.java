package com.maaii.automation.selenium.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson Liao on 20/10/15.
 */
public class AppiumService {

    private static AppiumDriverLocalService service = null;
    private static Map<Long, AppiumDriverLocalService> appiumDriverLocalServiceMap = new HashMap<Long, AppiumDriverLocalService>();

    public static void setService() {
        service = AppiumDriverLocalService.buildDefaultService();
    }

    public static AppiumDriverLocalService getService() {
        return service;
    }

    public static void startService() {
        service.start();
    }

    public static boolean serviceIsRunning() {
        return service.isRunning()?true:false;
    }

    public static void stopService() {
        if (service != null) {
            service.stop();
        }
    }

}
