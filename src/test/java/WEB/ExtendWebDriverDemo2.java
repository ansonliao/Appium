package WEB;

import com.maaii.automation.exception.IllegalLocatorIndexException;
import com.maaii.automation.exception.LocatorDisplayException;
import com.maaii.automation.exception.NoSuchLocatorException;
import com.maaii.automation.exception.NoSuchLocatorExistException;
import com.maaii.automation.file.YamlParser;
import com.maaii.automation.page.Action;
import com.maaii.automation.page.Action1;
import com.maaii.automation.selenium.ExtendWebElement;
import com.maaii.automation.testcase.AbstractBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by ansonliao on 23/12/2015.
 */
public class ExtendWebDriverDemo2 extends AbstractBaseTest {


    @Test(description = "Demo in Web of seleium in Fail", groups = {"Fail"})
    public void f2() throws IllegalLocatorIndexException, NoSuchLocatorExistException, LocatorDisplayException, NoSuchLocatorException, InterruptedException, IOException {
        Map eMsg = YamlParser.toMap("src/test/Resources/TestInfo/errorMsg.yaml");
        driver.get("http://www.google.com");
        Action.type((ExtendWebElement) page.getElement("kwInputField"), "Gebish");
        Action.clickAndWait((ExtendWebElement) page.getElement("searchBtn"), 1500);
        Assert.assertEquals(driver.getTitle().trim(), "Gebish - Google Search", eMsg.get("msg1").toString().trim());
    }
}