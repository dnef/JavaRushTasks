package com.javarush.task.task18.task1818;

import java.io.*;
import java.util.Scanner;

/* 
Два в одном
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();
        reader.close();
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        FileInputStream fileInputStream3 = new FileInputStream(file3);
        FileOutputStream fileOutputStream = new FileOutputStream(file1);

        byte[] buff2 = new byte[fileInputStream2.available()];
        fileInputStream2.read(buff2);
        fileInputStream2.close();
        byte[] buff3 = new byte[fileInputStream3.available()];
        fileInputStream3.read(buff3);
        fileInputStream3.close();
        fileOutputStream.write(buff2);
        fileOutputStream.write(buff3);
        fileOutputStream.close();
    }
}
