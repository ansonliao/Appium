package com.maaii.automation.annotation;

import com.maaii.automation.commons.Annotations;

import java.lang.reflect.Method;

/**
 * Created by ansonliao on 9/3/2016.
 */
public class AnnotationParser {

    public static synchronized Object getClassAnnotation(String className, Annotations annotation) {
        Object objectReturn = null;

        Class<?> rtClass = getReflectClass(className);
        switch (annotation) {
            case Description:
                Description description = null;
                if (rtClass.isAnnotationPresent(Description.class)) {
                    description = (Description) rtClass.getAnnotation(Description.class);
                    objectReturn = description;
                }
                break;
            case Author:
                Author author = null;
                if (rtClass.isAnnotationPresent(Author.class)) {
                    author = (Author) rtClass.getAnnotation(Author.class);
                }
                objectReturn = author;
                break;
            case Configration:
                Configration config = null;
                if (rtClass.isAnnotationPresent(Configration.class)) {
                    config = (Configration) rtClass.getAnnotation(Configration.class);
                    objectReturn = config;
                }
                break;
            default:
                // For enum: Locators
                Locators locatorFile = null;
                if (rtClass.isAnnotationPresent(Locators.class)) {
                    locatorFile = (Locators) rtClass.getAnnotation(Locators.class);
                    objectReturn = locatorFile;
                }
                break;
        }

        return objectReturn;
    }

    public static synchronized Object getMethodAnnotation(String className, Method methodName, Annotations annotation) {
        Object objectReturn = null;
        Method[] methods = getReflectClass(className).getMethods();

        for (Method method : methods) {
            if (method.getName().equals(methodName.getName())) {
                switch (annotation) {
                    case Description:
                        Description description = null;
                        if (method.isAnnotationPresent(Description.class)) {
                            description = (Description) method.getAnnotation(Description.class);
                            objectReturn = description;
                        }
                        break;
                    case Author:
                        Author author = null;
                        if (method.isAnnotationPresent(Author.class)) {
                            author = (Author) method.getAnnotation(Author.class);
                        }
                        objectReturn = author;
                        break;
                    case Configration:
                        Configration config = null;
                        if (method.isAnnotationPresent(Configration.class)) {
                            config = (Configration) method.getAnnotation(Configration.class);
                            objectReturn = config;
                        }
                        break;
                    default:
                        // For enum: Locators
                        Locators locatorFile = null;
                        if (method.isAnnotationPresent(Locators.class)) {
                            locatorFile = (Locators) method.getAnnotation(Locators.class);
                            objectReturn = locatorFile;
                        }
                        break;
                }
            }
        }

        return objectReturn;

    }

//    private static synchronized Object getAnnotation(Object op, Annotations annotation, AnnotationType annotationType) {
//        Object opeartionObject = op;
//        switch (annotation) {
//
//        }
//
//    }

    public static synchronized boolean annotationExistForClass(String className, Annotations annotation) {
        Class<?> rtClass = getReflectClass(className);
        boolean isExist = false;

        switch (annotation) {
            case Author:
                isExist = rtClass.isAnnotationPresent(Author.class) ? true : false;
                break;
            case Description:
                isExist = rtClass.isAnnotationPresent(Description.class) ? true : false;
                break;
            case Configration:
                isExist = rtClass.isAnnotationPresent(Configration.class) ? true : false;
                break;
            default:
                // For enum: Locators
                isExist = rtClass.isAnnotationPresent(Locators.class) ? true : false;
                break;
        }

        return isExist;
    }

    public static synchronized boolean annotationExistForMethod(String className, Method methodName, Annotations annotation) {
        boolean isExist = false;
        Method[] methods = getReflectClass(className).getMethods();

        for (Method method : methods) {
            if (method.getName().equals(methodName.getName())) {
                switch (annotation) {
                    case Author:
                        isExist = method.isAnnotationPresent(Author.class) ? true : false;
                        break;
                    case Description:
                        isExist = method.isAnnotationPresent(Description.class) ? true : false;
                        break;
                    case Configration:
                        isExist = method.isAnnotationPresent(Configration.class) ? true : false;
                        break;
                    default:
                        isExist = method.isAnnotationPresent(Locators.class) ? true : false;
                        break;
                }
            }
        }

        return isExist;
    }


    private static synchronized Class<?> getReflectClass(String className) {
        Class<?> classGot = null;
        try {
            classGot = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return classGot;
    }



    private enum AnnotationType {
        CLASS,
        METHOD
    }

    public static void main(String[] args) {
        Description description = (Description) getClassAnnotation("com.maaii.automation.annotation.Utility", Annotations.Description);
        System.out.println(description.value());
        Author author = (Author) getClassAnnotation("com.maaii.automation.annotation.Utility", Annotations.Author);
        System.out.println("Author: " + author.name());
        System.out.println("Group: " + author.group());

//        description = (Description) getMethodAnnotation("com.maaii.automation.annotation.Utility", "work", Annotations.Description);
//        System.out.println(description.value());
//        author = (Author) getMethodAnnotation("com.maaii.automation.annotation.Utility", "work", Annotations.Author);
//        System.out.println("Author: " + author.name());
//        System.out.println("Group: " + author.group());
    }
}
