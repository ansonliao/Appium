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

    public static synchronized void setStartedTime(Date date) {
        ExtentTest test = ExtentTestManager.getTest();
//        ExtentTestUtil.LogInfo("Set Test Started Time to: " + date);
        ExtentTestUtil.LogInfo(Utils.toBold("Set Test Started Time: " )+ Utils.withPre(date.toString()));
        test.setStartedTime(date);
    }

    public static synchronized void setEndedTime(Date date) {
        ExtentTest test =ExtentTestManager.getTest();
        ExtentTestUtil.LogInfo(Utils.toBold("Set Test Ended Time: ") + Utils.withPre(date.toString()));
        test.setEndedTime(date);
    }

    private static synchronized ExtentTest getExtentTest() {
        return ExtentTestManager.getTest();
    }
}
