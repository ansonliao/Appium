package com.maaii.automation.extentreport.Factory;

import com.relevantcodes.extentreports.ExtentReports;
import org.testng.ITestContext;

import java.io.File;

/**
 * Created by ansonliao on 18/12/2015.
 */
public class ExtentManager {
    private static ExtentReports extent;
    private static ITestContext iTestContext;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            File outPutDirectory = new File(iTestContext.getOutputDirectory());
            File resultDirectory = new File(outPutDirectory.getParentFile(), "ExtentReport");
//            System.out.println("outPutDirectory: " + outPutDirectory);
//            System.out.println("out parent: " + outPutDirectory.getParentFile());
//            System.out.println("resultDirectory: " + resultDirectory.toString());
            extent = new ExtentReports(resultDirectory + File.separator + "ExtentReport.html");
        }

        return extent;
    }

    public static void setOutPutDirectory(ITestContext iTestContext) {
        ExtentManager.iTestContext = iTestContext;
    }

    public static void writeReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void closeReport() {
        if (extent != null) {
            extent.close();
        }
    }
}
