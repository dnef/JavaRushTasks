package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
*/

public class Solution {
    public static void main(String... args) throws IOException {
        int seek =0;
        RandomAccessFile randomAccessFile = new RandomAccessFile(args[0],"rw");
        if(randomAccessFile.length()<Integer.parseInt(args[1])){
            seek = (int) randomAccessFile.length();
        }else{seek = Integer.parseInt(args[1]);}
        randomAccessFile.seek(seek);
        randomAccessFile.write(args[2].getBytes());
        randomAccessFile.close();
    }
}
