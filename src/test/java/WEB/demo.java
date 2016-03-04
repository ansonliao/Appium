package WEB;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by ansonliao on 30/8/15.
 */
public class demo {

    static WebDriver driver;

    public static void main(String[] agrs) throws IOException, ServiceException {
//        driver = new FirefoxDriver();
//
//        driver.get("http://www.google.com");
//
//        List<WebElement> listElement = driver.findElements(By.name("123456"));
//
//        System.out.println(!listElement.isEmpty()?true:false);
//
//        listElement = driver.findElements(By.name("btnK"));
//        System.out.println(!listElement.isEmpty() ? true : false);
//
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        List<WebElement> els = wait.until(new ExpectedCondition<List<WebElement>>() {
//            public List<WebElement> apply(WebDriver webDriver) {
//                List<WebElement> elements = webDriver.findElements(By.name("btnK"));
//
//                return elements;
//            }
//        });
//
//        System.out.println(els.size());
//
//        List<WebElement> elements = waitForElements(driver, "btnK");
//        if (elements == null)
//            System.out.println("elemets is null");
//        else
//            System.out.println(elements.size());
//        driver.quit();

        SpreadsheetService service =
                new SpreadsheetService("demo");

        URL SPREADSHEET_FEED_URL = new URL(
                "https://docs.google.com/spreadsheets/d/1GXACOzqU0Qe2dbpvJtfsiCS94XuGUOnj4L3jQkSfCgw/edit?usp=sharing"
        );

        SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
                SpreadsheetFeed.class);

        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

        SpreadsheetEntry spreadsheet = spreadsheets.get(0);
        System.out.println(spreadsheet.getTitle().getPlainText());

    }

    public static List<WebElement> waitForElements(final WebDriver dr, final String str) {
        List<WebElement> elements = null;

        WebDriverWait wait = new WebDriverWait(dr, 10);

        try {
            elements = wait.until(new ExpectedCondition<List<WebElement>>() {
                public List<WebElement> apply(WebDriver driver) {
                    List<WebElement> els = driver.findElements(By.name(str));

                    System.out.println("els size: " + els.size());
                    return els.size() > 0 ? els : null;
                }
            });
        } catch(Exception e) {
            System.out.println("els is null " + By.name(str).toString());
            elements = null;
        }

        return elements;
    }
}
