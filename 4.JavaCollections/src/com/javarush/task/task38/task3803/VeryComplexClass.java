package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object i = Integer.valueOf(42);
        String s = (String)i;
    }

    public void methodThrowsNullPointerException() {
        Object unknownObject = null;
        if(unknownObject.equals("knownObject")){
            System.err.println("This may result in NullPointerException if unknownObject is null");
        }

    }

    public static void main(String[] args) {

    }
}
