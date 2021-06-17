package com.javarush.task.task18.task1816;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Английские буквы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
            String file = args[0];
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            int simbol;
            String sim;
            int n=0;
            while ((simbol = reader.read()) !=-1){
                sim =  Character.toString((char) simbol);
                Pattern patlatletter = Pattern.compile("[a-zA-Z]{1}");
                Matcher english = patlatletter.matcher(sim);
                if(english.matches()){
                    n++;
                }
            }
            reader.close();
        System.out.println(n);
    }
}
