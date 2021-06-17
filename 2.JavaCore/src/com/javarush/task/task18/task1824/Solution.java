package com.javarush.task.task18.task1824;

import java.io.*;

import static java.lang.System.in;
import static java.lang.System.setOut;

/* 
Файлы и исключения
*/

public class Solution {
    public static void main(String[] args) {
        String file;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                file = reader.readLine();
                try(FileInputStream fileInputStream = new FileInputStream(file)){

                }catch (FileNotFoundException e){
                    System.out.println(file);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
