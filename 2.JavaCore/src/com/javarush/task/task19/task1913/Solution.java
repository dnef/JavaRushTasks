package com.javarush.task.task19.task1913;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

/* 
Выводим только цифры
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream out = System.out;
        ByteArrayOutputStream arr = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(arr);
        System.setOut(printStream);
        testString.printSomething();
        String print = arr.toString().replaceAll("[^0-9]","");
        System.setOut(out);
        System.out.println(print);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
