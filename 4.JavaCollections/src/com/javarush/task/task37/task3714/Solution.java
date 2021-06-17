package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        char[] romanChar = s.toCharArray();
        int current = 0;
        int old =0;
        int summ=0;
        for (int i = 0;i<romanChar.length;i++) {
            current = Roman.valueOf(String.valueOf(romanChar[i])).toInt();
                if (current > old) {
                    System.out.println("summ " + summ + " + " + (current-2*old));
                    System.out.println("current-2*old = "+current+" - 2*"+old);
                    summ += current-2*old;
                }
                else {
                        System.out.println("summ-" + summ + " + " + current);
                        summ += current;
                }
               old=current;
            }
        return summ;
    }
}
