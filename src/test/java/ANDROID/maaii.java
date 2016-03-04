package ANDROID;

import com.maaii.automation.exception.IllegalLocatorIndexException;
import com.maaii.automation.exception.LocatorDisplayException;
import com.maaii.automation.exception.NoSuchLocatorException;
import com.maaii.automation.exception.NoSuchLocatorExistException;
import com.maaii.automation.page.Action;
import com.maaii.automation.testcase.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by ansonliao on 4/3/2016.
 */
public class maaii extends AndroidBaseTest {

    @Test
    public void f1() throws IllegalLocatorIndexException, NoSuchLocatorExistException, LocatorDisplayException, NoSuchLocatorException, InterruptedException {
        Action.click(page.getElement("termsBtn"));
        Assert.assertEquals("Terms of Service", page.getElement("termsTitle").getElement().getText(), "Terms page's title incorrect.");
//        System.out.println(page.getElementNoWait("termsTitle").getElement().getText());

//        Set<String> contextNames = driver.getContextHandles();
//        for (String contextName : contextNames) {
//            System.out.println("ContextName: " + contextName);
//            if (contextName.contains("WEBVIEW")) {
//                driver.context(contextName);
//            }
//        }
//
//        System.out.println("Terms content title: " + driver.findElementById("maaii-terms-of-service").getText());
        Thread.sleep(10000);
        System.out.println("Start scroll...");
        int width = driver.manage().window().getSize().width;
        int heigh = driver.manage().window().getSize().height;

        driver.swipe(width/2, heigh/10*8, width/2, heigh/10*2, 1000);

        Thread.sleep(5000);
    }
}
