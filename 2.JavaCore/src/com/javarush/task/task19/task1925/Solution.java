package com.javarush.task.task19.task1925;

import java.io.*;
import java.util.ArrayList;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        FileWriter fileWriter = new FileWriter(args[1]);
        String line;
        String [] lineSplit;
        String out="";
        while ((line = reader.readLine())!=null){
            lineSplit = line.split(" ");
            for (int i=0;i< lineSplit.length;i++){
                if (lineSplit[i].length()>6){
                    out = out+lineSplit[i]+",";
                }
            }

        }
        if (!out.equals("") && out.length()!=0){
            out=out.substring(0,out.length()-1);
            fileWriter.write(out);
        }
        reader.close();
        fileWriter.close();

    }
}
