package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        TreeSet<Character> characters = new TreeSet<>();

        String s;
        while ((s = reader.readLine()) != null) {
            for (char ch : s.toLowerCase().toCharArray()) {
                if (ch >= 97 && ch <= 122)
                    characters.add(ch);
            }
        }

        reader.close();

        int count = 0;
        for (Character c : characters) {
            System.out.print(c);
            count++;
            if (count == 5) {
                break;
            }
        }
    }
}


