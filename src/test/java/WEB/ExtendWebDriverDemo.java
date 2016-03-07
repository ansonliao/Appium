package WEB;

import com.maaii.automation.exception.IllegalLocatorIndexException;
import com.maaii.automation.exception.LocatorDisplayException;
import com.maaii.automation.exception.NoSuchLocatorException;
import com.maaii.automation.exception.NoSuchLocatorExistException;
import com.maaii.automation.page.Action;
import com.maaii.automation.selenium.ExtendWebElement;
import com.maaii.automation.testcase.AbstractBaseTest;
import org.testng.annotations.Test;

/**
 * Created by ansonliao on 19/12/2015.
 */
public class ExtendWebDriverDemo extends AbstractBaseTest {

    @Test(description = "Demo in Web of selenium in Pass", groups = {"Pass"})
    public void f1() throws IllegalLocatorIndexException, NoSuchLocatorExistException, LocatorDisplayException, NoSuchLocatorException, InterruptedException {
        driver.get("http://www.google.com");
        Action.type(page.getExtElement("kwInputField"), "appium");
        Action.click(page.getExtElement("searchBtn"));
    }
}
