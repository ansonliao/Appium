package com.maaii.automation.ios;

import com.maaii.automation.exception.ConfigurationException;
import com.maaii.automation.file.YamlParser;
import com.maaii.automation.selenium.appium.AppiumService;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created by ansonliao on 31/7/15.
 */
public class IOSDriverManager {
    private static IOSDriver driver;
    private static Map configMap = null;
    private static String configYamlFilePath;
    private static double waitingTime = 0, imageSimilar = 0.6;


//    public IOSDriverManager(String configYamlFilePath) {
//        this.configYamlFilePath = configYamlFilePath;
//    }


    /**
     * Initialize IOSDriver instance
     * @param file
     * @throws FileNotFoundException
     * @throws ConfigurationException
     * @throws MalformedURLException
     */
    public static void setup(String file) throws IOException, ConfigurationException {
        configYamlFilePath = file;
        configMap = YamlParser.toMap(configYamlFilePath);

        if (Double.valueOf(configMap.get("waitingTime").toString()) > 0)
            waitingTime = Double.valueOf(configMap.get("waitingTime").toString());

        //TODO: check imageSimilar value whether in (0, 1]
        imageSimilar = Double.valueOf(configMap.get("imageSimilar").toString());


        File appDir = new File(configMap.get("appDir").toString());
        File app = new File(appDir, configMap.get("app").toString());
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (configMap.containsKey("browserName"))
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, configMap.get("browserName").toString());
        else
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");

        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Float.valueOf(configMap.get("platformVersion").toString()));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, configMap.get("deviceName").toString());
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        //driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver = new IOSDriver<WebElement>(AppiumService.getService().getUrl(), capabilities);
        setDriver(driver);

    }

    private static void setDriver(IOSDriver d) {
        driver = d;
    }

//    public static IOSDriver getInstance(String file) throws FileNotFoundException, ConfigurationException, MalformedURLException {
//        init(file);
//        return driver;
//    }

    public static IOSDriver getInstance() {
        return driver;
    }

    public static double getWaitingTime() {
        return waitingTime;
    }

    public static double getImageSimilar() {
        return imageSimilar;
    }


    /**
     * for test
     * @param args
     * @throws FileNotFoundException
     * @throws ConfigurationException
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, ConfigurationException, InterruptedException {
        IOSDriver iosDriver;
        String file = "res/config/sample4iOS.yaml";
        IOSDriverManager.setup(file);
        iosDriver = IOSDriverManager.getInstance();

        Thread.sleep(5000);
        iosDriver.quit();

    }
}
