package com.maaii.automation.commons;

/**
 * Created by ansonliao on 4/3/2016.
 */
public enum KeyWord {
    WEB, IOS, Android,

    TYPE, VALUE, INDEX, DESC,

    ID, NAME, XPATH, CLASSNAME, LINKTEXT,
    TAGNAME, AccessibilityId, AndroidUIAutomator,
    IosUIAutomation;

    public static String toStr(KeyWord kw) {
        return kw.toString().trim();
    }

    public static String toStrUpper(KeyWord kw) {
        return kw.toString().trim().toUpperCase();
    }

    public static String toStrLower(KeyWord kw) {
        return kw.toString().trim().toLowerCase();
    }

}
