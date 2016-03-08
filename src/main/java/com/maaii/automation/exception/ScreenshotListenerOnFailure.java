package com.maaii.automation.exception;

import com.maaii.automation.extentreport.Factory.ExtentTestManager;
import com.maaii.automation.selenium.Snapshot;
import com.maaii.automation.utils.Utils;
import com.maaii.automation.utils.extentreport.ExtentTestUtil;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by ansonliao on 8/3/2016.
 */
public class ScreenshotListenerOnFailure extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ITestContext iTestContext = iTestResult.getTestContext();
        String screenshotFile = Snapshot.takeScreenShot(iTestContext);

//        ExtentTestManager.getTest().log(
//                LogStatus.FAIL, "Failure Screenshot below: "
//                + ExtentTestManager.getTest().addScreenCapture(screenshotFile)
//        );

        ExtentTestUtil.LogFail(Utils.toBold("Test was fail and failure sceenshot attached below:")
                + ExtentTestManager.getTest().addScreenCapture(screenshotFile));
    }
}
