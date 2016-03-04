package com.maaii.automation.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by ansonliao on 7/2/2016.
 */
public class AnalysisAnnotation {
    public static void main(String[] args) throws  ClassNotFoundException {

        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());


        try {
            Class<?> rtClass = Class.forName("com.maaii.automation.annotation.Utility");
            Method[] methods = rtClass.getMethods();
            boolean descriptionExist = rtClass.isAnnotationPresent(Description.class);

            if (descriptionExist) {
                Description description = (Description)rtClass.getAnnotation(Description.class);
                System.out.println("Utility's description ----> " + description.value());

                for (Method method : methods) {
                    if (method.isAnnotationPresent(Author.class)) {
                        System.out.println("Method name: " + method.getName().trim());
                        Annotation[] annotations = method.getDeclaredAnnotations();

                        for (Annotation annotation : annotations) {
                            System.out.println("Annotation name: " + annotation.toString().trim());
                        }

                        Author author = (Author)method.getAnnotation(Author.class);
                        System.out.println("Utility's Author ----> " + author.name() + " from " + author.group());
                    }
                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
