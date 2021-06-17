package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();
    public static String one;
    public static String two;


    public static void main(String[] args) throws CorruptedDataException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Solution sl = new Solution();
        try {
            one = reader.readLine();
            two = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File(one);
            FileReader fileReader = new FileReader(file);
            BufferedReader readerLine = new BufferedReader(fileReader);
            String line = readerLine.readLine();
            while (line != null){
                allLines.add(line);
                line = readerLine.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File file = new File(two);
            FileReader fileReader = new FileReader(file);
            BufferedReader readerLine = new BufferedReader(fileReader);
            String line = readerLine.readLine();
            while (line != null){
                forRemoveLines.add(line);
                line = readerLine.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sl.joinData();
    }

    public void joinData() throws CorruptedDataException {
        String lines;
        boolean all = false;
        int dell;
        for(int i=0;i<forRemoveLines.size();i++){
            lines = forRemoveLines.get(i);
            if (allLines.indexOf(lines) !=-1){
                all=true;
            }else {all=false;}
        }
        if(all){
            for(int i=0;i<forRemoveLines.size();i++) {
                lines = forRemoveLines.get(i);
                dell = allLines.indexOf(lines);
                allLines.remove(dell);
            }
        }else{
        allLines.clear();
        throw new CorruptedDataException();
    }
    }
}
