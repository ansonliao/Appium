package com.maaii.automation.utils.extentreport;

import com.maaii.automation.extentreport.Factory.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Created by ansonliao on 18/12/2015.
 */
public class ExtentTestUtil {
    public static synchronized void LogInfo(String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.INFO, logInfo);
    }

    public static synchronized void LogPass(String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.PASS, logInfo);
    }

    public static synchronized void LogFail(String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.FAIL, logInfo);
    }

    public static synchronized void LogSkip(String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.SKIP, logInfo);
    }

    public static synchronized void LogError(String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.ERROR, logInfo);
    }

    public static synchronized void LogUnknown(String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.UNKNOWN, logInfo);
    }

    public static synchronized void LogWarning(String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.WARNING, logInfo);
    }

    private static synchronized ExtentTest getExtentTest() {
        return ExtentTestManager.getTest();
    }
}
