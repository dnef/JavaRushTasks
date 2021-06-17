package com.javarush.task.task18.task1807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        try {
            String file = reader.readLine();
            reader.close();
            FileInputStream fileInputStream = new FileInputStream(file);
            while (fileInputStream.available() >0){
               if( fileInputStream.read() == 44){
                   count++;
               }
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);

    }
}
