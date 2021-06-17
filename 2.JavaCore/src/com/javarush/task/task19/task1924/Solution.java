package com.javarush.task.task19.task1924;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();
    static {
        map.put(0,"ноль");
        map.put(1,"один");
        map.put(2,"два");
        map.put(3,"три");
        map.put(4,"четыре");
        map.put(5,"пять");
        map.put(6,"шесть");
        map.put(7,"семь");
        map.put(8,"восемь");
        map.put(9,"девять");
        map.put(10,"десять");
        map.put(11,"одиннадцать");
        map.put(12,"двенадцать");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader=new BufferedReader(new FileReader(file.readLine()));
        file.close();
        String line;
        String [] lineSplit;
        while ((line= reader.readLine())!=null){
            String out = "";
            lineSplit=line.split(" ");
            for (int i=0;i<lineSplit.length;i++){
                if(lineSplit[i].matches("\\d+")){
                    if(map.get(Integer.parseInt(lineSplit[i]))!=null){
                        lineSplit[i] = map.get(Integer.parseInt(lineSplit[i]));
                    }
                }
                out = out+lineSplit[i]+" ";

            }
            out = out.trim();
            System.out.println(out);
        }
        reader.close();

    }
}
