package ru.bogachev.hw3;

import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) {
        run(TestAnnotations.class);
    }

    public static void run(Class<?> testClass) {
        Class<?> clazz = testClass.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            //if (!)
        }
    }
}
