package com.javarush.task.task19.task1909;

import java.io.*;
import java.util.ArrayList;

/* 
Замена знаков
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        String strR;
        BufferedReader readerF = new BufferedReader(new FileReader(file1));
        BufferedWriter writerF = new BufferedWriter(new FileWriter(file2));
        while ((strR = readerF.readLine()) !=null){
            strR=strR.replaceAll("\\.","!");
            writerF.write(strR);
        }
        readerF.close();
        writerF.close();

    }
}
