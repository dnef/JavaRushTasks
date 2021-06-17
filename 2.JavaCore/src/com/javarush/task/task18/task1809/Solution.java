package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile1=null;
        String nameFile2 = null;
        try {
            nameFile1 = reader.readLine();
            nameFile2 = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {

            FileInputStream fileInputStream = new FileInputStream(nameFile1);
            FileOutputStream fileOutputStream = new FileOutputStream(nameFile2);

            byte[] buff = new byte[fileInputStream.available()];
            byte[] buff2 = new byte[fileInputStream.available()];
            fileInputStream.read(buff);
            fileInputStream.close();
            int k=0;
            for (int i=buff.length-1;i>=0;i--){
                buff2[k] = buff[i];
                k++;
            }
                fileOutputStream.write(buff2);
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
