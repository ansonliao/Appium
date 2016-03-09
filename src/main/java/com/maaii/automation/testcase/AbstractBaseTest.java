package com.maaii.automation.testcase;

import com.maaii.automation.commons.ExtentTestResult;
import com.maaii.automation.extentreport.Factory.*;
import com.maaii.automation.page.Page;
import com.maaii.automation.selenium.WebDriverManager;
import com.maaii.automation.utils.Utils;
import com.maaii.automation.utils.extentreport.ExtentTestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by ansonliao on 18/12/2015.
 */
public class AbstractBaseTest {
    public static ExtentReports extent;
    public ExtentTest test;
    public WebDriver driver;
    public Page page;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutPutDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeClass
    public void beforeClass() throws IOException {
        driver = WebDriverManager.getInstance();
        page = new Page("src/test/Resources/config/Google.yaml");
    }

    @BeforeMethod
    public void startEachTestMethod(Method method) {
        ExtentTestManager.startTest(method.getName(), getMethodDesc(method));

        // Test categories assigned
        if (getMethodGroups(method) != null) {
            ExtentTestManager.getTest().assignCategory(getMethodGroups(method));
        }

        test = ExtentTestManager.getTest();
        ExtentTestUtil.LogInfo(Utils.toBold("TEST START"), Utils.withPre(method.getName() + " [method]"));

        /**
         * TODO: insert test case author here
         */
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult iTestResult) {
        String className = iTestResult.getTestClass().getRealClass().toString().trim();

        ExtentTestManager.getTest().getTest().setStartedTime(getTime(iTestResult.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(iTestResult.getEndMillis()));

        switch (iTestResult.getStatus()) {
            case ExtentTestResult.PASS:
                ExtentTestUtil.LogPass(Utils.toBold("PASS"),
                        Utils.toBold("TEST END with PASS:") +
                                Utils.withPre(iTestResult.getMethod().getMethodName() + " [method]"));
                break;
            case ExtentTestResult.FAIL:
                ExtentTestUtil.LogFail(Utils.toBold("FAIL"),
                        Utils.withPre(getStackTrace(iTestResult.getThrowable())));
                ExtentTestUtil.LogFail(
                        Utils.toBold("FAIL"),
                        Utils.toBold("TEST END with FAIL:") +
                                Utils.withPre(iTestResult.getMethod().getMethodName() + " [method]"));
                break;
            case ExtentTestResult.SKIP:
                ExtentTestUtil.LogSkip(Utils.toBold("SKIP"),
                        Utils.toBold("TEST Skipped:") +
                                Utils.withPre(iTestResult.getMethod().getMethodName() + " [method]"));
                break;

            default:
                ExtentTestUtil.LogPass("PASS", "Test Passed");
        }

        ExtentTestManager.closeTest();
    }

    @AfterClass
    public void afterClass() {
        WebDriverManager.quitDriver();
    }

    /**
     *  After suite will be responsible to close the report properly at the end.
     *  You an have another afterSuite as well in the derived class and this one
     *  will be called in the end making it the last method to be called in test exe
     */
    @AfterSuite
    public void generateReport() {
        ExtentManager.writeReport();
        ExtentManager.closeReport();
    }


    protected synchronized String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    private synchronized String getMethodNameDesc(Method method) {
        Test test = method.getAnnotation(Test.class);

        return test.testName().trim();
    }

    private synchronized String getMethodDesc(Method method) {
        Test test = method.getAnnotation(Test.class);
        return test.description().trim();
    }

    private synchronized String[] getMethodGroups(Method method) {
        Test test = method.getAnnotation(Test.class);

        return test.groups().length > 0 ? removeDuplicatedArrayItem(test.groups()) : null;
    }

    private synchronized String[] removeDuplicatedArrayItem(String[] array) {
        Set<String> set = new HashSet<String>();
        set.addAll(Arrays.asList(array));
        //TODO Remove duplicated item in ignore-case

        return set.toArray(new String[0]);
    }

    private synchronized Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        return calendar.getTime();
    }
}
