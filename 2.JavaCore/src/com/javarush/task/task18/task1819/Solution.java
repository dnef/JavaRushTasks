package com.javarush.task.task18.task1819;

import java.io.*;

/* 
Объединение файлов
*/

public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1 = reader.readLine();
            String file2 = reader.readLine();
            reader.close();
            FileInputStream fileInputStream1 = new FileInputStream(file1);
            FileInputStream fileInputStream2 = new FileInputStream(file2);
            FileOutputStream fileOutputStream = new FileOutputStream(file1);

            byte[] buff2 = new byte[fileInputStream1.available()];
            fileInputStream1.read(buff2);
            fileInputStream1.close();
            byte[] buff3 = new byte[fileInputStream2.available()];
            fileInputStream2.read(buff3);
            fileInputStream2.close();
            fileOutputStream.write(buff3);
            fileOutputStream.write(buff2);
            fileOutputStream.close();
        }
}
