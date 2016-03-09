package com.maaii.automation.utils.extentreport;

import com.maaii.automation.extentreport.Factory.ExtentTestManager;
import com.maaii.automation.utils.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Date;

/**
 * Created by ansonliao on 18/12/2015.
 */
public class ExtentTestUtil {
    public static synchronized void LogInfo(String stepName, String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.INFO, stepName, logInfo);
    }

    public static synchronized void LogPass(String stepName, String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.PASS, stepName, logInfo);
    }

    public static synchronized void LogFail(String stepName, String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.FAIL, stepName, logInfo);
    }

    public static synchronized void LogSkip(String stepName, String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.SKIP, stepName, logInfo);
    }

    public static synchronized void LogError(String stepName, String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.ERROR, stepName, logInfo);
    }

    public static synchronized void LogUnknown(String stepName, String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.UNKNOWN, stepName, logInfo);
    }

    public static synchronized void LogWarning(String stepName, String logInfo) {
        ExtentTest test = ExtentTestManager.getTest();

        test.log(LogStatus.WARNING, stepName, logInfo);
    }

    public static synchronized void setStartedTime(Date date) {
        ExtentTest test = ExtentTestManager.getTest();
        test.setStartedTime(date);
    }

    public static synchronized void setEndedTime(Date date) {
        ExtentTest test =ExtentTestManager.getTest();
        test.setEndedTime(date);
    }

    private static synchronized ExtentTest getExtentTest() {
        return ExtentTestManager.getTest();
    }
}
