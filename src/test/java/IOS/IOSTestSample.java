package IOS;

import com.maaii.automation.commons.Variables;
import com.maaii.automation.exception.*;
import com.maaii.automation.ios.IOSDriverManager;
import com.maaii.automation.selenium.appium.AppiumService;
import com.maaii.automation.page.Action;
import com.maaii.automation.page.Page;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.*;

import java.io.IOException;

/**
 * Created by cpuser on 4/8/15.
 */
public class IOSTestSample {

    private IOSDriver driver;
    private Page page;
    private String confYamlFile = "src/test/Resources/config/sample4iOS.yaml";

//    @Test
//    public void demo() throws NoSuchLocatorException, InterruptedException, NoSuchLocatorExistException, LocatorDisplayException, IllegalLocatorIndexException {
//        //for pass
//        Action.click(page.getElement("btns"));
//
//        //for error
//        Action.click(page.getElement("xbtn"));
//        Thread.sleep(3000);
//    }

    @BeforeClass
    public void beforeClass() {
        AppiumService.setService();
        AppiumService.startService();

        if (AppiumService.getService() == null || !AppiumService.serviceIsRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }
    }
    @BeforeMethod
    public void beforeMethod() throws IOException, ConfigurationException {
        Variables.TEST_TYPE = System.getProperty("testType");
        IOSDriverManager.setup(confYamlFile);
        driver = IOSDriverManager.getInstance();
        page = new Page(confYamlFile);

    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @AfterClass
    public void afterClass() {
        AppiumService.stopService();
    }
}
