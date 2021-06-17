package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        FileInputStream fi = new FileInputStream(file);
        Integer max = fi.read();
        Integer temp;
        while (fi.available() >0){
            temp=fi.read();
            if(temp>max){
                max=temp;
            }
        }
        fi.close();
        System.out.println(max);
    }
}
