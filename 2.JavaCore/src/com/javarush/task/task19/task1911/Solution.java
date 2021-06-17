package com.javarush.task.task19.task1911;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Ридер обертка
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream out =System.out;
        ByteArrayOutputStream arrByte = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(arrByte);
        System.setOut(printStream);
        testString.printSomething();
        String print = arrByte.toString().toUpperCase();
        System.setOut(out);
        System.out.println(print);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
