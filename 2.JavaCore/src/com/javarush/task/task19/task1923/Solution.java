package com.javarush.task.task19.task1923;

import java.io.*;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        FileWriter fileWriter = new FileWriter(args[1]);
        //BufferedWriter writer = new BufferedWriter(fileWriter);
        String line;
        while ((line=reader.readLine())!=null){
            String[] lineSplit = line.split(" ");
            for (int i=0;i< lineSplit.length;i++){
                if(lineSplit[i].matches(".*\\d+.*")){
                    fileWriter.write(lineSplit[i]+" ");
                }
            }
        }
        reader.close();
       // writer.close();
        fileWriter.close();
    }
}
