package ANDROID;

import com.maaii.automation.annotation.Author;
import com.maaii.automation.testcase.TestBase;
import org.testng.annotations.Test;

/**
 * Created by ansonliao on 9/3/2016.
 */
@Author(name = "Anson Liao", group = "Automation Team")
public class Demo extends TestBase {

    @Author(name = "Anson Liao", group = "Automation Team")
    @Test
    public void f1() {
        System.out.println("In test F1()");
    }
}
