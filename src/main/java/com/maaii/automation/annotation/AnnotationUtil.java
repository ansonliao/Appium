package com.maaii.automation.annotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ansonliao on 1/3/2016.
 */
public class AnnotationUtil {
    private static Map<Long, Method[]> methodMap = new HashMap<Long, Method[]>();
    private static Map<Long, Class<?>> classMap = new HashMap<Long, Class<?>>();

    public static synchronized String getMethodAuthor(String className, String methodName) {
        String author = "";

        Method[] methods = getMethods(getClass(className));
        for (Method method : methods) {
            if (method.toString().trim().toUpperCase().equals(methodName.trim().toUpperCase())) {
                if (method.isAnnotationPresent(Author.class)) {
                    author = ((Author) method.getAnnotation(Author.class)).name().trim();
                }
            }
        }

        return author;
    }

    public static synchronized String getMethodAuthorGroup(String className, String methodName) {
        String group = "";

        Method[] methods = getMethods(getClass(className));
        for (Method method : methods) {
            if (method.toString().trim().toUpperCase().equals(methodName.trim().toUpperCase())) {
                if (method.isAnnotationPresent(Author.class)) {
                    group = ((Author) method.getAnnotation(Author.class)).group().trim();
                }
            }
        }

        return group;
    }

    private static synchronized Class<?> getClass(String className) {

//        if (!classMap.containsKey(threadID)) {
//            classMap.put(threadID, Class.forName(className))
//
//        }
        Class<?> rtClass = null;

        try {
            rtClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rtClass != null ? rtClass : null;
    }

    private static synchronized Method[] getMethods (Class<?> rtClass) {
        return rtClass.getMethods().length != 0 ? rtClass.getMethods() : null;
    }


}
