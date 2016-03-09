package com.maaii.automation.testcase;

import com.maaii.automation.annotation.AnnotationParser;
import com.maaii.automation.annotation.Author;
import com.maaii.automation.commons.Annotations;
import org.junit.BeforeClass;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

/**
 * Created by ansonliao on 9/3/2016.
 */
public class TestBase {
    public String a = null;

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before class...");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After class...");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        String className = method.getDeclaringClass().toString().trim().split(" ")[1];
        System.out.println(className);
        if (AnnotationParser.annotationExistForClass(className, Annotations.Author)) {
            Author author = (Author) AnnotationParser.getClassAnnotation(className, Annotations.Author);
            System.out.println("Class author: " + author.name());
            System.out.println("Author group: " + author.group());
        }
        else {
            System.out.println("No Class Author annotation found.");
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult iTestResult, Method method) {
//        String className = iTestResult.getTestClass().getRealClass().toString().trim().split(" ")[1];
//        System.out.println(className);
//
//        Author author = (Author) AnnotationParser.getMethodAnnotation(className, method, Annotations.Author);
//        System.out.println(author.name());


    }
}
