package com.javarush.task.task16.task1630;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/* 
Последовательный вывод файлов
*/

public class Solution {

    public static String firstFileName;
    public static String secondFileName;

    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
            reader.close();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) throws InterruptedException {

        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {

        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {

        private String fileName;
        StringBuffer stringBuffer = new StringBuffer();

        @Override
        public void setFileName(String fullFileName) {
            this.fileName = fullFileName;
        }

        @Override
        public String getFileContent() {

            return stringBuffer.toString();
        }

        @Override
        public void start() {
            super.start();
        }

        @Override
        public void run() {
            BufferedReader fileReader = null;
            try {
                fileReader = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                System.out.println("Flie " + fileName + " not found!");
            }
            try {
                while (fileReader.ready()){
                    stringBuffer.append(fileReader.readLine());
                    stringBuffer.append(" ");
                }
                fileReader.close();
            } catch (IOException e) {
                System.out.println("IOException");
            }
        }
    }
}
