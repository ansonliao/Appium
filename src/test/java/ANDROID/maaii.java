package ANDROID;

import com.maaii.automation.exception.IllegalLocatorIndexException;
import com.maaii.automation.exception.LocatorDisplayException;
import com.maaii.automation.exception.NoSuchLocatorException;
import com.maaii.automation.exception.NoSuchLocatorExistException;
import com.maaii.automation.page.Action;
import com.maaii.automation.testcase.AndroidBaseTest;
import org.testng.annotations.Test;

/**
 * Created by ansonliao on 4/3/2016.
 */
public class maaii extends AndroidBaseTest {

    @Test
    public void f1() throws IllegalLocatorIndexException, NoSuchLocatorExistException, LocatorDisplayException, NoSuchLocatorException, InterruptedException {
        Action.clickAndWait(page.getElement("termsBtn"), 10000);
    }
}
