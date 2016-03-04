package com.maaii.automation.commons;

import com.maaii.automation.android.AndroidDriverManager;
import com.maaii.automation.ios.IOSDriverManager;
import com.maaii.automation.selenium.WebDriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cpuser on 3/8/15.
 */
public class ScreenShotOnFailure extends TestListenerAdapter {

    private static String separator = File.separator;

    @Override
    public void onTestFailure (ITestResult tr) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        WebDriver driver;
        IOSDriver idr;
        AndroidDriver adr;
        File scrFile = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String destDir = Constants.SCREENSHOT_DIRECTORY;
        String destFile = dateFormat.format(new Date()) + ".png";
        String scrPath = (new File(destDir + separator + destFile)).getAbsolutePath();

//        String testType = System.getProperty("testType");

        if (!(new File(destDir).isDirectory())){
            new File(destDir).mkdirs();
        }

        if (Variables.TEST_TYPE.equalsIgnoreCase("ios")) {
            idr = IOSDriverManager.getInstance();
            scrFile = ((TakesScreenshot) idr).getScreenshotAs(OutputType.FILE);
        }

        if (Variables.TEST_TYPE.equalsIgnoreCase("android")) {
            adr = AndroidDriverManager.getInstance();
            scrFile = ((TakesScreenshot) adr).getScreenshotAs(OutputType.FILE);
        }

        if (Variables.TEST_TYPE.equalsIgnoreCase("web")) {
            driver = WebDriverManager.getInstance();
            scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        }


        try {
            FileUtils.copyFile(scrFile, new File(destDir + separator + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reporter.setEscapeHtml(false);
        //TestHistory/" +
        //System.getProperty("buildNumber") + "/surefire-reports"
        Reporter.log(
                "Saved " +
                        "<a href=" +
                        Constants.JENKINS_WS_DIRECTORY +
                        File.separator +
                        "TestHistory" +
                        File.separator +
                        System.getProperty("buildNumber") +
                        "/target/surefire-reports/Screenshots" +
                        File.separator +
                        destFile +
                        " ><b>Screenshot</b></a>"
        );

    }


}