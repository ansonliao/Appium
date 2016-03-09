package com.maaii.automation.utils;

import com.maaii.automation.commons.Variables;
import com.maaii.automation.exception.IllegalArgumentException;
import com.maaii.automation.utils.extentreport.ExtentTestUtil;
import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by ansonliao on 7/3/2016.
 */
public class GlobalConfigReader {
    private static Logger logger = Logger.getLogger(ConfigReader.class);
    private static int retryCount = 0;

    private static final String RETRYCOUNT = "retrycount";
    private static final String CONFIGFILE = "config/config.properties";

    private static int waitTime;
    private static double imageSimilar = 0.7;
    private static final String WAITTIME = "waitingtime";
    private static final String IMGSIMILAR = "imagesimilar";

    public static void readConfig() throws IllegalArgumentException {
        Properties properties = getConfig(CONFIGFILE);
        if (properties != null) {
            String sRetryCount = null;
            String sWaitTime = null;
            String sImageSimilar = null;

            Enumeration<?> en = properties.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();

                if (key.toLowerCase().equals(RETRYCOUNT)) {
                    sRetryCount = properties.getProperty(key);
                }
                if (key.toLowerCase().equals(WAITTIME)) {
                    waitTime = Integer.parseInt(properties.getProperty(key));
                    sWaitTime = properties.getProperty(key);
                }
                if (key.toLowerCase().equals(IMGSIMILAR)) {
                    sImageSimilar = properties.getProperty(key);
                }
            }

            if (sRetryCount != null) {
                sRetryCount = sRetryCount.trim();
                try {
                    retryCount = Integer.parseInt(sRetryCount);
                } catch (final NumberFormatException e) {
                    String emsg = "Parse " + RETRYCOUNT + " [" + sRetryCount + "] from String to Int Exception.";
                    ExtentTestUtil.LogError(Utils.toBold("ERROR"), Utils.withPre(emsg));
                    throw new NumberFormatException(emsg);
                }
            }

            if (sWaitTime != null) {
                sWaitTime = sWaitTime.trim();
                try {
                    waitTime = Integer.parseInt(sWaitTime);
                }
                catch (final NumberFormatException e) {
                   String emsg =  "Parse " + WAITTIME + " [" + sWaitTime + "] from String to Int Exception.";
                    ExtentTestUtil.LogError(Utils.toBold("ERROR"), Utils.withPre(emsg));
                    throw new NumberFormatException(emsg);
                }
            }

            if (sImageSimilar != null) {
                sImageSimilar = sImageSimilar.trim();
                try {
                    imageSimilar = Double.parseDouble(sImageSimilar);
                }
                catch (final NumberFormatException e) {
                    String emsg =  "Parse " + IMGSIMILAR + " [" + sImageSimilar + "] from String to Int Exception.";
                    ExtentTestUtil.LogError(Utils.toBold("ERROR"), Utils.withPre(emsg));
                    throw new NumberFormatException(emsg);
                }
            }

            if (imageSimilar < 0 || imageSimilar > 1) {
                String emsg = "IMAGE SIMILAR value should be in range [0, 1]";
                throw new IllegalArgumentException(emsg);
            }

            setGlobalWaitTimet();
            setGlobalRetryCount();
            setGlobalImageSimilar();
        }
    }

    public static int getRetryCount() {
        return retryCount;
    }

    public static int getWaitTime() {
        return waitTime;
    }

    public static double getImageSimilar() {
        return imageSimilar;
    }

    private static void setGlobalWaitTimet() {
        Variables.DEFAULT_WAIT_TIME_IN_MILLIS = GlobalConfigReader.getWaitTime();
    }

    private static void setGlobalRetryCount() {
        Variables.RETRYCOUNT = GlobalConfigReader.getRetryCount();
    }

    private static void setGlobalImageSimilar() {
        Variables.IMAGESIMILAR = GlobalConfigReader.getImageSimilar();
    }

    /**
     *
     * @param propertyFileName
     *
     * @return
     */
    private static Properties getConfig(String propertyFileName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertyFileName));
        } catch (FileNotFoundException e) {
            properties = null;
            logger.warn("FileNotFoundException:" + propertyFileName);
        } catch (IOException e) {
            properties = null;
            logger.warn("IOException:" + propertyFileName);
        }
        return properties;
    }

    public static void main(String[] agrs) throws Exception {
        GlobalConfigReader.readConfig();
        System.out.println(GlobalConfigReader.getRetryCount());
        System.out.println(GlobalConfigReader.getImageSimilar());
        System.out.println(GlobalConfigReader.getWaitTime());

    }
}
