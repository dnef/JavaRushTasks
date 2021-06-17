package com.javarush.task.task18.task1814;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {
        public String fileName;
    public TxtInputStream(String fileName) throws IOException, UnsupportedFileNameException {
        super(fileName);
        String exp=fileName.substring(fileName.length()-4);
        if(!exp.equalsIgnoreCase(".txt")){
            super.close();
            throw new UnsupportedFileNameException();
        }
        }

    public static void main(String[] args) throws IOException, UnsupportedFileNameException {
        new TxtInputStream("c://Безымянный.png");
    }
}

