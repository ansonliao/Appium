package com.maaii.automation.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ansonliao on 18/12/2015.
 */
public class Snapshot {

    public synchronized static String takeScreenShot(ITestContext iTestContext) {
        File outPutDirectory = new File(iTestContext.getOutputDirectory());
        String resultDirectory = (new File(outPutDirectory.getParentFile(), "ExtentReport")).getAbsolutePath();
        String destFile = getDateTime() + ".png";

        if (!(new File((resultDirectory)).isDirectory())) {
            new File(resultDirectory).mkdirs();
        }

        WebDriver driver = WebDriverManager.getInstance();

        File scImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scImage, new File(resultDirectory + File.separator + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultDirectory + File.separator + destFile;


    }
    private static synchronized String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

        return dateFormat.format(new Date());
    }
}
