package com.maaii.automation.selenium.appium;

import com.maaii.automation.utils.Utils;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ansonliao on 3/3/2016.
 */
public class AppiumServer {

    private static Map<Long, AppiumDriverLocalService> appiumDriverLocalServiceMap = new HashMap<Long, AppiumDriverLocalService>();

    public static synchronized AppiumDriverLocalService gerService() {
        Long threadID = Utils.getThreadID();

        if (!appiumDriverLocalServiceMap.containsKey(threadID)) {
            AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
            appiumDriverLocalServiceMap.put(threadID, service);
        }

        return appiumDriverLocalServiceMap.get(threadID);
    }


    public static synchronized void startService() {

        appiumDriverLocalServiceMap.get(Utils.getThreadID()).start();
    }


    public static synchronized boolean serviceIsRunning() {
        return appiumDriverLocalServiceMap.get(Utils.getThreadID()).isRunning() ? true : false;
    }


    public static synchronized void stopService() {
        AppiumDriverLocalService service = appiumDriverLocalServiceMap.get(Utils.getThreadID());

        if (service != null) {
            service.stop();
        }
    }

}
