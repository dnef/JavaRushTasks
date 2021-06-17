package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw");
        String text = args[2];
        byte[] bytetext = new byte[text.length()];
        randomAccessFile.seek(Integer.parseInt(args[1]));
        randomAccessFile.read(bytetext, 0, text.length());
        String str = new String(bytetext);
        if (str.equals(text)){
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write("true".getBytes());
        }else{
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write("false".getBytes());
        }
    }
}
