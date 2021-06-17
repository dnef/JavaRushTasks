package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.runtime.JSType.isNumber;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String file1 = reader.readLine();
//        String file2 = reader.readLine();
//        reader.close();
        String file1 = "d://temp.txt";
        String file2 = "d://temp1.txt";
        BufferedReader fileRead = new BufferedReader(new FileReader(file1));
        BufferedWriter fileWrite = new BufferedWriter(new FileWriter(file2));
        String str;
        String strR="";
        String strW="";
        while ((str = fileRead.readLine()) != null){
        strR=strR+str;
        }
        fileRead.close();
        String [] s = strR.split("\\s");
        for (int i=0;i<s.length;i++){
            if (Solution.isNumeric(s[i])) {
                strW = strW+s[i]+" ";
            }
        }
        fileWrite.write(strW);
        fileWrite.close();

    }
    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

//можно так
//while (fr.ready()) {
//        String numbers = fr.readLine();                //считываем все в строку
//        Pattern pat = Pattern.compile("(\\b\\d+\\b)");//создаем паттерн для поиска совпадений
//        Matcher mat = pat.matcher(numbers);           //матчеру передаем строку - он сам будет сравнивать
//        while (mat.find()) {                           //пока есть совпадения матчер проходит по значения нашей строки
//
//        bw.write(mat.group() + " ");       //записываем в новый файл найденые совпадения и пробел
//        System.out.println(mat.group());         //выводим найденые совпадения