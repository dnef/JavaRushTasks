package com.javarush.task.task19.task1922;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        BufferedReader reader1 =new BufferedReader(new FileReader(file));
        String line;

        while ((line=reader1.readLine())!=null){
            String[] lineArr = null;
            int koll=0;
            lineArr = line.split(" ");
            for (int i=0;i<lineArr.length;i++){
                for (int k=0;k<words.size();k++) {
                    if (lineArr[i].equals(words.get(k))) {
                        koll++;
                    }
                }
            }


            System.out.println(koll);
            if(koll==2){
                System.out.println(line);
            }
        }
        reader1.close();
    }
}
