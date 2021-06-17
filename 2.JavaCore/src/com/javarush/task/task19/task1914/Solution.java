package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream consoleStream = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        testString.printSomething();
        String res = outputStream.toString().replaceAll("\n", "");
        String[] str = res.split(" ");
        if (str[1].equals("+"))
            res += Integer.parseInt(str[0]) + Integer.parseInt(str[2]);
        if (str[1].equals("*"))
            res += Integer.parseInt(str[0]) * Integer.parseInt(str[2]);
        if (str[1].equals("-"))
            res += Integer.parseInt(str[0]) - Integer.parseInt(str[2]);

        System.setOut(consoleStream);
        System.out.print(res);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

