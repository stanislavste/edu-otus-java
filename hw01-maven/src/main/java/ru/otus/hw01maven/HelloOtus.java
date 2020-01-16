package ru.otus.hw01maven;

import com.google.common.base.CharMatcher;

public class HelloOtus {
    public static void main(String[] args) {
        CharMatcher charMatcher = new CharMatcher() {
            @Override
            public boolean matches(char c) {
                return c == 'D';
            }
        };
        if (charMatcher.matches('D')) System.out.println("Hello Otus");
    }
}
