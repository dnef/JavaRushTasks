package com.javarush.task.task19.task1907;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        //String file = "d://temp.txt";
        FileReader fileReader = new FileReader(file);
        char symbol;
        String str="";
        while (fileReader.ready()){
            symbol= (char) fileReader.read();
            str = str+symbol;
        }
        fileReader.close();
        int i=0;
        Matcher matcher = Pattern.compile("\\bworld\\b").matcher(str);
        while (matcher.find()){
            i++;
        }
        System.out.println(i);
    }
}
