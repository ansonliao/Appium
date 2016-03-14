package com.maaii.automation.testcase;

import com.maaii.automation.android.AndroidDriverManager;
import com.maaii.automation.annotation.AnnotationParser;
import com.maaii.automation.annotation.Author;
import com.maaii.automation.annotation.Configration;
import com.maaii.automation.commons.Annotations;
import com.maaii.automation.commons.ExtentTestResult;
import com.maaii.automation.commons.TestPlatform;
import com.maaii.automation.commons.Variables;
import com.maaii.automation.exception.ConfigurationException;
import com.maaii.automation.exception.IllegalArgumentException;
import com.maaii.automation.extentreport.Factory.ExtentManager;
import com.maaii.automation.extentreport.Factory.ExtentTestManager;
import com.maaii.automation.page.Page;
import com.maaii.automation.utils.GlobalConfigReader;
import com.maaii.automation.utils.Utils;
import com.maaii.automation.utils.extentreport.ExtentTestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import org.apache.xpath.operations.And;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by ansonliao on 3/3/2016.
 */
public class AndroidBaseTest {
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected AndroidDriver driver;
    protected Page page;

    static {
        try {
            GlobalConfigReader.readConfig();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    @BeforeSuite
    public void extentSetup(ITestContext context) throws IllegalArgumentException {
        ExtentManager.setOutPutDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeClass
    public void beforeClass() throws IOException, ConfigurationException {
        String className = this.getClass().getName();
        if (!AnnotationParser.annotationExistForClass(className, Annotations.Configration)) {
            throw new ConfigurationException("Test Case Class level configuration file was not found.[missed Configuration annotation in class level]");
        }

        Configration configration = (Configration) AnnotationParser.getClassAnnotation(className, Annotations.Configration);
        String configFile = configration.value();
        Variables.TestConfigMap.put(Utils.getThreadID(), configFile);

        driver = AndroidDriverManager.getInstance();

        if (AndroidDriverManager.getLocatorYaml() != null) {
            System.out.println("Test Case Locator YAML: " + AndroidDriverManager.getLocatorYaml());
//            page = new Page(AndroidDriverManager.getLocatorYaml());
            page = new Page(configFile);
        }

        Variables.TEST_TYPE = TestPlatform.ANDROID;
    }

    @BeforeMethod
    public void startEachTestMethod(Method method) {
        String className = method.getDeclaringClass().toString().trim().split(" ")[1];
        ExtentTestManager.startTest(method.getName(), getMethodDesc(method));

        // Test categories assigned
        if (getMethodGroups(method) != null) {
            ExtentTestManager.getTest().assignCategory(getMethodGroups(method));
        }

        /**
         * TODO: insert test case author here
         */
        if (AnnotationParser.annotationExistForMethod(className, method, Annotations.Author)) {
            Author author = (Author) AnnotationParser.getMethodAnnotation(className, method, Annotations.Author);
            ExtentTestManager.getTest().assignAuthor(author.name() + " - " + author.group());
        }

        test = ExtentTestManager.getTest();
        ExtentTestUtil.LogInfo(Utils.toBold("TEST START"), Utils.withPre(method.getName() + " [method]"));


    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult iTestResult) {
        String className = iTestResult.getTestClass().getRealClass().toString().trim();

        ExtentTestUtil.setStartedTime(getTime(iTestResult.getStartMillis()));
        ExtentTestUtil.setStartedTime(getTime(iTestResult.getEndMillis()));

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
        AndroidDriverManager.quitDriver();
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

    private synchronized String getDeclaringClassName(Method method) {
        return method.getDeclaringClass().toString().trim().split(" ")[1];
    }
}
