package com.maaii.automation.selenium;

import org.openqa.selenium.WebElement;

/**
 * Created by AnsonLiao on 20/10/15.
 * @author AnsonLiao
 */
public class ExtendWebElement {
    private WebElement element;
    private String desc;

    public void setElement(WebElement element) {
        this.element = element;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public WebElement getElement() {
        return element;
    }

}
