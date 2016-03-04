package com.maaii.automation.file;

import com.maaii.automation.exception.ConfigurationException;
import com.maaii.automation.exception.IllegalLocatorIndexException;
import com.maaii.automation.exception.NoSuchLocatorException;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by ansonliao on 31/7/15.
 */
public class YamlParser {

    /**
     * parse Yaml
     * @param yamlFilePath
     * @return Map
     * @throws IOException
     */
    public static Map toMap(String yamlFilePath) throws IOException {
        InputStream input = new FileInputStream(new File(yamlFilePath));
        Yaml yaml = new Yaml();
        HashMap map = (HashMap) yaml.load(input);
        input.close();
        return map;
    }

    public static HashMap getLocatorMap(Map yamlMap, String key) throws NoSuchLocatorException, IllegalLocatorIndexException {
        HashMap locatorMap = null;

        if (!yamlMap.containsKey(key)) {
            String emsg =
                    "Locator: [" + key + "]\n" +
                    "ERROR  : Locator is not exist in locator file.";
            throw new NoSuchLocatorException(emsg);
        }

        locatorMap = (HashMap) yamlMap.get(key);
        // Check index whether is digital
        if (locatorMap.containsKey("index")) {
            Pattern pattern = Pattern.compile("[0-9]*");
            if (!pattern.matcher(locatorMap.get("index").toString()).matches()) {
                String emsg =
                        "Locator index illegal.\n" +
                        "Locator index should be digit.";
                throw new IllegalLocatorIndexException(emsg);
            }


            int index = Integer.parseInt(locatorMap.get("index").toString());
            // Check index is in legal range
            if (index <= 0) {
                String emsg =
                        "Locator index should be great than ZERO.\n" +
                        "Current index value: [" + index + "].";
                throw new IllegalLocatorIndexException(emsg);
            }
        }

        return locatorMap;

    }


    /**
     * get Map's key value
     * @param map
     * @param key
     * @return Map
     */
    public static String getKeyValue(Map map, String key) {
        return map.get(key).toString();
    }

    /**
     * for test
     * @param agrs
     * @throws FileNotFoundException
     */
    public static void main(String[] agrs) throws IOException, ConfigurationException {
        /**
         * file existed case
         */
        String file = "res/config/sample4iOS.yaml";

        Map map = toMap(file);
//        System.out.println("browser name is: " + map.get("browserName").toString());
//        System.out.println("platform name is: " + map.get("platformName").toString());
        System.out.println("platform version is: " + map.get("platformVersion").toString());
        System.out.println("app is: " + map.get("app").toString());
        System.out.println("appDir is: " + map.get("appDir").toString());
        System.out.println("imageSimilar is: " + Double.valueOf(map.get("imageSimilar").toString()));
        System.out.println("waiting time is: " + Double.valueOf(map.get("waitingTime").toString()));

        /**
         * error case
         */
//        file = "res/willBeError.yaml";
//        map = toMap(file);
//        System.out.println("type is: " + map.get("type").toString());
//        System.out.println("browser name is: " + map.get("browserName").toString());
//        System.out.println("platform name is: " + map.get("platformName").toString());
//        System.out.println("platform version is: " + map.get("platformVersion").toString());
//        System.out.println("app is: " + map.get("app").toString());
//        System.out.println("appDir is: " + map.get("appDir").toString());
//        System.out.println("imageSimilar is: " + map.get("imageSimilar").toString());
//        System.out.println("waiting time is: " + map.get("waitingTime").toString());

    }
}
