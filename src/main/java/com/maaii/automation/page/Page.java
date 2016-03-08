package com.maaii.automation.page;

import com.maaii.automation.android.AndroidDriverManager;
import com.maaii.automation.android.AndroidDriverWait;
import com.maaii.automation.android.ExpectedConditionForAndroid;
import com.maaii.automation.commons.KeyWord;
import com.maaii.automation.commons.Variables;
import com.maaii.automation.exception.IllegalLocatorIndexException;
import com.maaii.automation.exception.LocatorDisplayException;
import com.maaii.automation.exception.NoSuchLocatorException;
import com.maaii.automation.exception.NoSuchLocatorExistException;
import com.maaii.automation.extentreport.Factory.ExtentTestManager;
import com.maaii.automation.file.YamlParser;
import com.maaii.automation.ios.ExpectedConditionForIOS;
import com.maaii.automation.ios.IOSDriverManager;
import com.maaii.automation.ios.IOSDriverWait;
import com.maaii.automation.selenium.ExtendWebElement;
import com.maaii.automation.selenium.WebDriverManager;
import com.maaii.automation.utils.extentreport.ExtentTestUtil;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by ansonliao on 4/8/15.
 */
public class Page {
    //    private WebDriver webDriver;
    private static String confYamlFile = null;
    private static String locatorYamlFile = null;
    private Map locatorMap = null;

//    private static final String WEBTESTTYPE = "WEB";
//    private static final String IOSTESTTYPE = "IOS";
//    private static final String ANDROIDTESTTYPE = "ANDROID";
//
//    private static final String LOCATORTYPE = "type";
//    private static final String LOCATORVALUE = "value";
//    private static final String LOCATORINDEX = "index";
//    private static final String LOCATORDESC = "desc";

    public Page(String file) throws IOException {
        this.confYamlFile = file;
//        this.setupDriverInstance(Variables.TEST_TYPE);

        this.getLocatorYamlFile(this.confYamlFile);
        this.getLocatorMap();
        this.getWaitTime();
    }

//    private void setupDriverInstance (String TEST_TYPE) {
//        if (TEST_TYPE.equalsIgnoreCase("WEB")) {
//            this.webDriver = WebDriverManager.getInstance();
//        }
//    }

    /**
     *
     * @param key
     * @return
     * @throws NoSuchLocatorException
     * @throws NoSuchLocatorExistException
     * @throws LocatorDisplayException
     * @throws IllegalLocatorIndexException
     */
    public synchronized ExtendWebElement getExtElement(String key) throws NoSuchLocatorException, NoSuchLocatorExistException, LocatorDisplayException, IllegalLocatorIndexException {
        return getLocator(key, true);
    }

    /**
     *
     * @param key
     * @return
     * @throws NoSuchLocatorException
     * @throws NoSuchLocatorExistException
     * @throws LocatorDisplayException
     * @throws IllegalLocatorIndexException
     */
    public synchronized ExtendWebElement getExtElementNoWait(String key) throws NoSuchLocatorException, NoSuchLocatorExistException, LocatorDisplayException, IllegalLocatorIndexException {
        return getLocator(key, false);
    }

    public synchronized WebElement getElement(String key) throws IllegalLocatorIndexException, NoSuchLocatorExistException, LocatorDisplayException, NoSuchLocatorException {
        return getLocator(key, true).getElement();
    }

    public synchronized WebElement getElementNoWait(String key) throws IllegalLocatorIndexException, NoSuchLocatorExistException, LocatorDisplayException, NoSuchLocatorException {
        return getLocator(key, false).getElement();
    }

    /**
     *
     * @param key
     * @return
     * @throws IllegalLocatorIndexException
     * @throws NoSuchLocatorException
     */
    public synchronized List<WebElement> getElements(String key) throws IllegalLocatorIndexException, NoSuchLocatorException {
        return getLocators(key, true);
    }

    /**
     *
     * @param key
     * @return
     * @throws IllegalLocatorIndexException
     * @throws NoSuchLocatorException
     */
    public synchronized List<WebElement> getElementsNoWait(String key) throws IllegalLocatorIndexException, NoSuchLocatorException {
        return getLocators(key, false);
    }

    /**
     *
     * @param key
     * @param wait
     * @return
     * @throws NoSuchLocatorException
     * @throws NoSuchLocatorExistException
     * @throws LocatorDisplayException
     * @throws IllegalLocatorIndexException
     */
    private synchronized ExtendWebElement getLocator(String key, boolean wait) throws NoSuchLocatorException, NoSuchLocatorExistException, LocatorDisplayException, IllegalLocatorIndexException {
        ExtendWebElement extendWebElement = new ExtendWebElement();
        WebElement element = null;
        boolean isList = false;
        String type = null;
        String value = null;
        String elementDesc = "";
        int index = 0;

        Map map = YamlParser.getLocatorMap(this.locatorMap, key);

        // get element's key and value
        type = YamlParser.getKeyValue(map, KeyWord.toStrLower(KeyWord.TYPE));
        value = YamlParser.getKeyValue(map, KeyWord.toStrLower(KeyWord.VALUE));
        // get element index
        if (map.containsKey(KeyWord.toStrLower(KeyWord.INDEX))) {
            index = Integer.parseInt(map.get(KeyWord.toStrLower(KeyWord.INDEX)).toString()) - 1;
            isList = true;
        }

        if (map.containsKey(KeyWord.toStrLower(KeyWord.DESC))) {
            String desc = map.get(KeyWord.toStrLower(KeyWord.DESC)).toString().trim();
            elementDesc = desc.length() == 0 ? "" : desc;

//            if (elementDesc.length() == 0) {
//                elementDesc = "";
//            }
        }

        By by = this.getBy(type, value);

//        if (isList) {
//            element = waitForLocators(by, wait).get(index);
//        }
//        else {
//            element = waitForLocator(by, wait);
//        }

        element = isList ? waitForLocators(by, wait).get(index) : waitForLocator(by, wait);;

        if (!waitLocatorToBeDisplayed(element)) {
            element = null;
//            System.out.println("element is null.");
            ExtentTestUtil.LogWarning("Element is null");
        }

        //debug
//        System.out.println("element string: " + element.getClass());
//        System.out.println("element desc: " + elementDesc);

        extendWebElement.setElement(element);
        extendWebElement.setDesc(elementDesc);

        return extendWebElement;
    }

    /**
     * Get Elements
     * @param key
     * @param wait
     * @return
     * @throws IllegalLocatorIndexException
     * @throws NoSuchLocatorException
     */
    private synchronized List<WebElement> getLocators(String key, boolean wait) throws IllegalLocatorIndexException, NoSuchLocatorException {
        String type = null, value = null;

        Map map = YamlParser.getLocatorMap(this.locatorMap, key);
        type = map.get(KeyWord.toStrLower(KeyWord.TYPE)).toString();
        value = map.get(KeyWord.toStrLower(KeyWord.VALUE)).toString();

        return waitForLocators(this.getBy(type, value), wait);
    }

    /**
     * Get By object of WebElement
     * @param type By method
     * @param value WebElement locate value
     * @return By type
     */
    private synchronized static By getBy(String type, String value) {
        By by = null;

        if (type.equalsIgnoreCase(KeyWord.toStr(KeyWord.ID))) {
            by = By.id(value);
        }
        if (type.equalsIgnoreCase(KeyWord.toStr(KeyWord.NAME))) {
            by = By.name(value);
        }
        if (type.equalsIgnoreCase(KeyWord.toStr(KeyWord.XPATH))) {
            by = By.xpath(value);
        }
        if (type.equalsIgnoreCase("className")) {
            by = By.className(value);
        }
        if (type.equalsIgnoreCase("linkText")) {
            by = By.linkText(value);
        }
        if (type.equalsIgnoreCase("tagName")) {
            by = By.tagName(value);
        }
        // For mobileBy, Appium
        if (type.equalsIgnoreCase("AccessibilityId")){
            by = MobileBy.AccessibilityId(value);
        }
        if (type.equalsIgnoreCase("AndroidUIAutomator")){
            by = MobileBy.AndroidUIAutomator(value);
        }
        if (type.equalsIgnoreCase("IosUIAutomation")){
            by = MobileBy.IosUIAutomation(value);
        }



        return by;
    }


    /**
     * Wait for a element
     * @param by
     * @param wait
     * @return Located element
     * @throws NoSuchLocatorException
     */
    private synchronized WebElement waitForLocator(final By by, boolean wait) throws NoSuchLocatorException {
        WebElement element = null;

        if (wait) {
            try {
                switch (Variables.TEST_TYPE) {
                    case WEB:
                        element = new WebDriverWait(WebDriverManager.getInstance(), Variables.DEFAULT_WAIT_TIME_IN_MILLIS / 1000)
                                .until(new ExpectedCondition<WebElement>() {
                                    public WebElement apply(WebDriver webDriver) {
                                        return WebDriverManager.getInstance().findElement(by);
                                    }
                                });
                        break;
                    case IOS:
                        /**
                         * TODO add iOS handle code here
                         */
                        break;
                    default:
                        element = new AndroidDriverWait(AndroidDriverManager.getInstance(), Variables.DEFAULT_WAIT_TIME_IN_MILLIS / 1000)
                                .until(new ExpectedConditionForAndroid<WebElement>() {
                                    public WebElement apply(AndroidDriver androidDriver) {
                                        return AndroidDriverManager.getInstance().findElement(by);
                                    }
                                });
                        break;
                }
            } catch (Exception e) {
                String emsg = "Locator ["
                        + by.toString()
                        + "] was not exist until "
                        + Variables.DEFAULT_WAIT_TIME_IN_MILLIS
                        + " milliseconds.";

                throw new NoSuchLocatorException(emsg);
            }
        }
        else
            element = this.getLocatorNoWait(by);


        return element;
    }

    /**
     * Get a element without wait
     * @param by
     * @return
     */
    private synchronized WebElement getLocatorNoWait(By by) {
        WebElement el = null;

        switch (Variables.TEST_TYPE) {
            case WEB:
                el = WebDriverManager.getInstance().findElement(by);
                break;
            case IOS:
                /**
                 * TODO: Add iOS handle code here
                 */
                break;
            default:
                el = AndroidDriverManager.getInstance().findElement(by);
                break;
        }

        return el;
    }

    /**
     * Wait for elements
     * @param by
     * @param wait whether need to wait the elements
     * @return List of located elements in List
     * @throws NoSuchLocatorException
     */
    private synchronized List<WebElement> waitForLocators(final By by, boolean wait) throws NoSuchLocatorException {
        List<WebElement> elements = null;

        if (wait) {
            try {
                switch (Variables.TEST_TYPE) {
                    case WEB:
                        elements = new WebDriverWait(WebDriverManager.getInstance(), Variables.DEFAULT_WAIT_TIME_IN_MILLIS / 1000)
                                .until(new ExpectedCondition<List<WebElement>>() {
                                    public List<WebElement> apply(WebDriver driver) {
                                        List<WebElement> els = WebDriverManager.getInstance().findElements(by);
                                        return els.size() > 0 ? els : null;
                                    }
                                });
                        break;
                    case IOS:
                        /**
                         * TODO: Add iOS handle code here
                         */
                        break;
                    default:
                        elements = new AndroidDriverWait(AndroidDriverManager.getInstance(), Variables.DEFAULT_WAIT_TIME_IN_MILLIS / 1000)
                                .until(new ExpectedConditionForAndroid<List<WebElement>>() {
                                    public List<WebElement> apply(AndroidDriver androidDriver) {
                                        List<WebElement> els = AndroidDriverManager.getInstance().findElements(by);
                                        return  els.size() > 0 ? els : null;
                                    }
                                });
                        break;
                }
            } catch (Exception e) {
                String emsg = "Locator list ["
                        + by.toString()
                        + "] was not exist until "
                        + Variables.DEFAULT_WAIT_TIME_IN_MILLIS
                        + " milliseconds.";

                throw new NoSuchLocatorException(emsg);

            }
        }
        else
            elements = this.getLocatorsNoWait(by);

        return elements;
    }

    /**
     * Get elements without wait
     * @param by
     * @return Elements in List
     */
    private synchronized List<WebElement> getLocatorsNoWait(final By by) {
        List<WebElement> elements = null;

        switch (Variables.TEST_TYPE) {
            case WEB:
                elements = WebDriverManager.getInstance().findElements(by);
                break;
            case IOS:
                /**
                 * TODO: Add iOS handle code here
                 */
                break;
            default:
                elements = AndroidDriverManager.getInstance().findElements(by);
                break;
        }

        return elements;
    }

    /**
     * Wait for a element to be displayed in specify duration.
     * @param element
     * @return
     */
    private synchronized boolean waitLocatorToBeDisplayed(final WebElement element) throws LocatorDisplayException {
        boolean wait = false;
        if (element == null) {
            return false;
        }

        try {
            switch (Variables.TEST_TYPE) {
                case WEB:
                    wait = new WebDriverWait(WebDriverManager.getInstance(), Variables.DEFAULT_WAIT_TIME_IN_MILLIS / 1000)
                            .until(new ExpectedCondition<Boolean>() {
                                public Boolean apply(WebDriver webDriver) {
                                    return element.isDisplayed();
                                }
                            });
                    break;
                case IOS:
                    /**
                     * TODO: Add iOS handle code here
                     */
                    break;
                default:
                    wait = new AndroidDriverWait(AndroidDriverManager.getInstance(), Variables.DEFAULT_WAIT_TIME_IN_MILLIS / 1000)
                            .until(new ExpectedConditionForAndroid<Boolean>() {
                                public Boolean apply(AndroidDriver androidDriver) {
                                    return element.isDisplayed();
                                }
                            });
                    break;
            }
        } catch (Exception e) {
            String emsg = "Locator ["
                    + element.toString()
                    + "] was not displayed in "
                    + Variables.DEFAULT_WAIT_TIME_IN_MILLIS
                    + " milliseconds.";

            throw new LocatorDisplayException(emsg);
        }

        return wait;
    }


    /**
     * Wait for a element to be disappeared in spcify duration.
     * @param element
     * @return
     */
    private synchronized boolean waitLocatorToBeNotDisplayed(final WebElement element) throws LocatorDisplayException {
        boolean wait = false;

        if (element == null) {
            return false;       //element was disappeared
        }

        try {
            switch (Variables.TEST_TYPE) {
                case WEB:
                    wait = new WebDriverWait(WebDriverManager.getInstance(), Variables.DEFAULT_WAIT_TIME_IN_MILLIS / 1000)
                            .until(new ExpectedCondition<Boolean>() {
                                public Boolean apply(WebDriver webDriver) {
                                    return !element.isDisplayed();
                                }
                            });
                    break;
                case IOS:
                    /**
                     * TODO: Add iOS handle code here
                     */
                    break;
                default:
                    wait = new AndroidDriverWait(AndroidDriverManager.getInstance(), Variables.DEFAULT_WAIT_TIME_IN_MILLIS / 1000)
                            .until(new ExpectedConditionForAndroid<Boolean>() {
                                public Boolean apply(AndroidDriver androidDriver) {
                                    return !element.isDisplayed();
                                }
                            });
                    break;
            }

        } catch (Exception e) {
            String emsg =
                    "Locator [" +
                            element.toString() +
                            "] was displayed in " +
                            Variables.DEFAULT_WAIT_TIME_IN_MILLIS +
                            " milliseconds.";
            throw new LocatorDisplayException(emsg);

        }

        return wait;
    }

    /**
     * Verify specify string present in current page.
     * @param keyString
     * @return
     */
    public synchronized boolean verifyTextPresent(String keyString) {
        return WebDriverManager.getInstance().getPageSource().contains(keyString);
    }


    /**
     * Check element display in current page or not.
     * @param el The element for checking whether display or not.
     * @return Element whether display in boolean.
     */
    public synchronized boolean elementIsDisplayed(WebElement el) {
        return el.isDisplayed();
    }

    public synchronized boolean verifyElementExist(By by) {
        boolean isExist = true;
        List<WebElement> eList = null;

        switch (Variables.TEST_TYPE) {
            case WEB:
                eList = WebDriverManager.getInstance().findElements(by);
                break;
            case IOS:
                /**
                 * TODO: Add iOS handle code here
                 */
                break;
            default:
                eList = AndroidDriverManager.getInstance().findElements(by);
                break;
        }

        return !eList.isEmpty() ? true : false;

    }


    private void getLocatorYamlFile(String file) throws IOException {

        if (this.locatorYamlFile == null) {
            Map m = YamlParser.toMap(file);

            // Get locator yaml file path from configuration yaml file
            this.locatorYamlFile = m.get("locatorYaml").toString();
        }
    }

    private void getLocatorMap() throws IOException {

        if (this.locatorMap == null) {
            this.locatorMap = YamlParser.toMap(this.locatorYamlFile);
        }
    }

    private void getWaitTime() throws IOException {
        if (YamlParser.toMap(this.confYamlFile).containsKey("waitingTime"))
            Variables.DEFAULT_WAIT_TIME_IN_MILLIS =
                    Integer.valueOf(YamlParser.toMap(this.confYamlFile).get("waitingTime").toString());
    }

}
