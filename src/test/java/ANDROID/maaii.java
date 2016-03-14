package ANDROID;

import com.maaii.automation.annotation.Author;
import com.maaii.automation.annotation.Configration;
import com.maaii.automation.annotation.Description;
import com.maaii.automation.commons.SWIPEDIRECTION;
import com.maaii.automation.exception.IllegalLocatorIndexException;
import com.maaii.automation.exception.LocatorDisplayException;
import com.maaii.automation.exception.NoSuchLocatorException;
import com.maaii.automation.exception.NoSuchLocatorExistException;
import com.maaii.automation.page.Action;
import com.maaii.automation.testcase.AndroidBaseTest;
import com.maaii.automation.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ansonliao on 4/3/2016.
 */

@Configration("src/test/Resources/config/maaii.properties")
public class maaii extends AndroidBaseTest {

    @Author(name = "Anson Liao", group = "Automation Team")
    @Test(description = "New user in register flow", groups = {"Function Test"})
    public void testcaseRegister() throws IllegalLocatorIndexException, NoSuchLocatorExistException, LocatorDisplayException, NoSuchLocatorException, InterruptedException {
        Action.click(page.getExtElement("continueBtn"));
//        Action.click(page.getExtElement("phoneCallAllowBtn"));
//        Action.click(page.getExtElement("contactAllowBtn"));
        Action.click(page.getExtElement("countryCodeSelector"));

        Action.click(page.getExtElement("countrySearch"));
        Action.type(page.getExtElement("countryInput"), "hong");
        Action.click(page.getExtElement("hkRegion"));

        Action.type(page.getExtElement("phoneNoInput"), "68795625");
        Action.type(page.getExtElement("userNameInput"), "testing");
        Action.click(page.getExtElement("continueBtn2"));
        Action.click(page.getExtElement("registerConfirm"));
        Action.click(page.getExtElement("connect2FBSkip"));
//        Action.clickAndWait(page.getExtElement("toturialPageSkip"), 1000);
        Action.swipeDirection(SWIPEDIRECTION.RIGHT);
        Action.swipeDirection(SWIPEDIRECTION.RIGHT);
        Action.swipeDirection(SWIPEDIRECTION.RIGHT);
        Action.swipeDirection(SWIPEDIRECTION.RIGHT);

        Action.click(page.getExtElement("tutorialCompleteBtn"));
        Assert.assertTrue(page.getElement("userName").getText().contains("testing"), "Register user name incorrected.");
        Assert.assertTrue(page.getElement("userName2").getText().contains("testing"), "Register user name incorrected. [User Name Field 2.]");
        System.out.println(page.getElement("userPhone").getText().replaceAll(" ", "").trim());
        Assert.assertTrue(Utils.removeAllSpace(page.getElement("userPhone").getText()).contains("+852"), "Register region incorrect, expect: [Hong Kong] with [+852].");

        // fail case
        Assert.assertTrue(Utils.removeAllSpace(page.getElement("userPhone").getText()).contains("68795625"), "Register user phone incorrect.");
    }

    @Test
    public void TestFail() {
        Assert.assertTrue(false, "Test Fail");
    }
}
