package ru.bogachev.hw3;

import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) {
        run(TestAnnotations.class);
    }

    public static void run(Class<?> testClass) {
        Class<?> clazz = testClass.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            try {
                System.out.println(method.getName());
            } catch (Exception e){
                System.out.println("Exception: " + clazz.getCanonicalName());
            }
        }

    }
}
