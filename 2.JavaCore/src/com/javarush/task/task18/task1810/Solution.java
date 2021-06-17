package com.javarush.task.task18.task1810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws DownloadException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file;
        try {
        while ((file=reader.readLine())!=null){
            FileInputStream read = new FileInputStream(file);
            if (read.available()<1000){
                reader.close();
                read.close();
                throw new DownloadException();
            }
        }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static class DownloadException extends Exception {

    }
}
