package maven;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LambdaExpressionsClass {
    public static void main(String[] args) {

        Runnable runnable = () -> System.out.println("Hello world!");
        Thread t = new Thread(runnable);
        t.start();
        String[] array1 = {"1"};
        String[] array2 = {"3", "3", "3"};
        String[] array3 = {"2", "2"};

        List<String[]> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);

        /*Comparator<String[]> sortByLength = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return  o2.length - o1.length;
            }
        };*/

        arrays.sort(((o1, o2) -> o1.length - o2.length));



        Comparator<String[]> sortByWordsLength = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int length1 = 0;
                int length2 = 0;
                for (String s : o1) {
                    length1 += s.length();
                }
                for (String s : o2) {
                    length2 += s.length();
                }
                return length1 - length2;
            }
        };

        int m = 0;
        //arrays.sort(sortByWordsLength);
        for (String[] x : arrays) {
            for (String s : x) {
                System.out.print(s + " ");
            }
            System.out.println();
        };
    }
}
