package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        String rez = "";
        if (reader != null) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str = bufferedReader.readLine();
            char[] strArr = new char[str.length()];
            for (int i = 0; i < str.length(); i++) {
                strArr[i] = (char) (str.charAt(i) + key);
            }
            rez = String.valueOf(strArr);
        }
        return rez;
    }
}
