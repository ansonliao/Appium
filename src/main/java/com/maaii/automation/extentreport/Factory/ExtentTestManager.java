package com.maaii.automation.extentreport.Factory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ansonliao on 18/12/2015.
 */
public class ExtentTestManager {
    static Map<Long, ExtentTest> extentTestMap = new HashMap<Long, ExtentTest>();
    private static ExtentReports extent = ExtentManager.getInstance();

    public static synchronized ExtentTest getTest() {
        if (extentTestMap.containsKey(getCurrentThreadID())) {
            return extentTestMap.get(getCurrentThreadID());
        }

        // system log, this shouldnt happen but in this crazy times if it did happen log it.
        return null;
    }

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public static synchronized ExtentTest startTest(String testName, String testDesc) {
        ExtentTest test = extent.startTest(testName, testDesc);
        extentTestMap.put(getCurrentThreadID(), test);

        return test;
    }

    private static synchronized Long getCurrentThreadID() {
        return Thread.currentThread().getId();
    }

    public static synchronized void closeTest() {
        extent.endTest(extentTestMap.get(getCurrentThreadID()));
    }

    public static synchronized void closeTest(ExtentTest test) {
        if (test != null) {
            extent.endTest(test);
        }
    }
}
