package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(byteArrayOutputStream);
        System.setOut(stream);
        testString.printSomething();
        String zamena = byteArrayOutputStream.toString();
        String[] zam = zamena.split(System.lineSeparator());
        zamena = "";
        for (int i=0;i<zam.length;i++){
            zamena = zamena+zam[i]+System.lineSeparator();
            if((i+1)%2==0){
                zamena=zamena+"JavaRush - курсы Java онлайн"+System.lineSeparator();
            }
        }
        System.setOut(out);
        System.out.println(zamena);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
