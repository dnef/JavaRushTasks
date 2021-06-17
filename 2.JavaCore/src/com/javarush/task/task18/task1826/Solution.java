package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) {
        String param = args[0];
        String fileInputName = args[1];
        String fileOutputName = args[2];
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        int key = 777;
        byte[] fileInput = null;
        byte [] fileOutput = null;
        try {
           fileInputStream = new FileInputStream(fileInputName);
           fileOutputStream = new FileOutputStream(fileOutputName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        switch (param){
            case "-e":
                try {
                    fileInput = new byte[fileInputStream.available()];
                    fileInputStream.read(fileInput,0,fileInput.length);
                    fileOutput = cript(fileInput,key);
                    fileOutputStream.write(fileOutput,0,fileOutput.length);
                    fileInputStream.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "-d" :
                try {
                    fileInput = new byte[fileInputStream.available()];
                    fileInputStream.read(fileInput,0,fileInput.length);
                    fileOutput = cript(fileInput,key);
                    fileOutputStream.write(fileOutput,0,fileOutput.length);
                    fileInputStream.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private static byte[] cript(byte[] fileInput,int key) {
        byte[] temp = new byte[fileInput.length];
        for (int i=0;i<temp.length;i++){
            temp[i] = (byte) (fileInput[i] ^ key);
        }
        return temp;
    }

}
