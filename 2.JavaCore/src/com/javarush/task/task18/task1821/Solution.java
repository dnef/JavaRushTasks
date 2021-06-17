package com.javarush.task.task18.task1821;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String file = args[0];
        //String file = "d://temp.txt";
        FileInputStream fileInputStream=new FileInputStream(file);
        int sym;
        int koll;
        char symbol;
        TreeMap<Integer, Integer> list = new TreeMap<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        while ((sym=fileInputStream.read()) != -1){
            list.put(sym,0);
            arrayList.add(sym);
        }
        for (Integer arr:arrayList){
            koll = list.get(arr)+1;
            list.put(arr,koll);
        }
        fileInputStream.close();
        for (Map.Entry<Integer,Integer> entry: list.entrySet()){
            int s=entry.getKey();
            symbol = (char)s;
            System.out.println(symbol+" "+entry.getValue());
        }

    }
}
