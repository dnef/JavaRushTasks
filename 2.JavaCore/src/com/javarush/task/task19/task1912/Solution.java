package com.javarush.task.task19.task1912;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Ридер обертка 2
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream out = System.out;
        ByteArrayOutputStream arr = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(arr);
        System.setOut(printStream);
        testString.printSomething();
        String print = arr.toString().replaceAll("te","\\?\\?");
        System.setOut(out);
        System.out.println(print);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}