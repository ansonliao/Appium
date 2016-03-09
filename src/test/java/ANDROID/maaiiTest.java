package ANDROID;

import com.maaii.automation.commons.TestPlatform;
import com.maaii.automation.commons.Variables;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by ansonliao on 3/3/2016.
 */
public class maaiiTest {

    private AndroidDriver<WebElement> driver;
    private static AppiumDriverLocalService service;
    private static  final String deviceID = "emulator-5554";

    @BeforeClass
    public void beforeClass() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started.");
        }

        Variables.TEST_TYPE = TestPlatform.ANDROID;
        File appFile = new File("/Users/ansonliao/Desktop/Maaii.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceID);
        capabilities.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10000);
        driver = new AndroidDriver<WebElement>(service.getUrl(), capabilities);
//        driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @AfterClass
    public static void afterClass() {
        if (service != null) {
            service.stop();
        }
    }

    @Test
    public void firstPageTermsTest() throws InterruptedException {
        Thread.sleep(10000);
        WebElement termsBtn = driver.findElementById("com.maaii.test:id/btn_terms");
        termsBtn.click();
        Thread.sleep(10000);
    }
}
