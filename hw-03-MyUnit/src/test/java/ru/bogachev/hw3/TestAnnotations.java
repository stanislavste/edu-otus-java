package ru.bogachev.hw3;

import jdk.jfr.StackTrace;
import ru.bogachev.hw3.annotations.*;

public class TestAnnotations {
    TestAnnotations() {
        System.out.println("Constructor begin!");
    }

    @BeforeAll
    static void beforeAllMethod() {
        System.out.println("Before All");
    }

    @BeforeEach
    public void beforeEach1Method() {
        System.out.println("Before each 1");
    }
    @BeforeEach
    public void beforeEach2Method() {
        System.out.println("Before each 1");
    }
    @BeforeEach
    public void beforeEach3Method() {
        System.out.println("Before each 1");
    }

    @AfterEach
    public void afterEachMethod() {
        System.out.println("After each");
    }

    @AfterAll
    static void afterAllMethod() {
        System.out.println("After All");
    }

    @Test
    public void testOne() {
        System.out.println("testOne");
    }

    @Test
    public void testTwo() {
        System.out.println("testTwo");
    }


}
