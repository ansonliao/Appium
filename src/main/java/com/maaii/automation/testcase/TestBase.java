package com.maaii.automation.testcase;

import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

/**
 * Created by ansonliao on 9/3/2016.
 */
public class TestBase {

    @BeforeClass
    public void beforeClass(ITestContext context) {
        String className = this.getClass().getName();
        System.out.println("Before class... " + className);
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After class... ");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        System.out.println("Before method... ");
    }

    @AfterMethod
    public void afterMethod(ITestResult iTestResult, Method method) {
        System.out.println("After mehtod... ");
    }
}
