package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        String file;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!(file=reader.readLine()).equals("exit")){
            ReadThread readThread = new ReadThread(file);
            readThread.start();
        }
        reader.close();
        //resultMap.forEach((k,v)-> System.out.println(k+"---"+v));

    }

    public static class ReadThread extends Thread {
        String fileName;
        Integer byteFile;
        Integer max = 0;
        Integer key;
        HashMap<Integer, Integer> mapSymb = new HashMap<>();

        public ReadThread(String fileName) throws FileNotFoundException {
            //implement constructor body
            this.fileName = fileName;
        }

        @Override
        public void run() {
            synchronized (resultMap) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(fileName);
                    while (fileInputStream.available() > 0) {
                        byteFile = fileInputStream.read();
                        mapSymb.put(byteFile, mapSymb.getOrDefault(byteFile, 0) + 1);
                    }
                    fileInputStream.close();
                    mapSymb.forEach((k, v) -> {
                        if (v > max) {
                            max = v;
                        }
                    });
                    for (Map.Entry<Integer, Integer> entry : mapSymb.entrySet()) {
                        if (entry.getValue() == max) {
                            key = entry.getKey();
                        }
                    }
                    resultMap.put(fileName, key);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            // implement file reading here - реализуйте чтение из файла тут
        }
    }

