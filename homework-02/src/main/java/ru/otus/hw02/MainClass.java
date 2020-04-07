package ru.otus.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        DIYarrayList<Integer> dIYArrayList = new DIYarrayList<>();
        List<Integer> strings = new ArrayList<>();
        for (int i = 20; i > 0; i--) {
            strings.add(i);
        }
        dIYArrayList.addAll(strings);
        List<Integer> string2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            string2.add(i);
        }
        dIYArrayList.addAll(string2);
        System.out.println("my arrraylist size: " + dIYArrayList.size());

        //List<Integer> exampleCopy = new ArrayList<>();
        List<? super Integer> exampleCopy = new DIYarrayList<>();
        for (int i = 0; i < dIYArrayList.size(); i++) {
            exampleCopy.add(i, 0);
        }

        Collections.copy(exampleCopy, dIYArrayList);
        System.out.println("example copy size: " + exampleCopy.size());
        for (Object item : exampleCopy) {
            System.out.print(item + " ");
        }
        System.out.println();
        Collections.sort(dIYArrayList);
        for (Integer item : dIYArrayList) {
            System.out.print(item + " ");
        }
        System.out.println("конец");
    }
}
