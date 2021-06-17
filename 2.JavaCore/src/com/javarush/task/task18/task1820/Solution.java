package com.javarush.task.task18.task1820;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/* 
Округление чисел
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
//        String file1 = "d://temp.txt";
//        String file2 = "d://temp1.txt";
        reader.close();
        LinkedList<Float> list=addList(file1);
        writeListtoFile(list,file2);
    }

    private static void writeListtoFile(LinkedList<Float> list,String file2) throws IOException {
        String str = "";
        int number;
        for (Float n:list){
            number=Math.round(n);
            str = str+number+" ";
        }
        FileWriter fileWriter= new FileWriter(file2);
        fileWriter.write(str);
        fileWriter.close();
    }

    private static LinkedList<Float> addList(String file1) throws IOException {
        FileReader fileReader = new  FileReader(file1);
        Scanner scan = new Scanner(fileReader);
        LinkedList<Float> list = new LinkedList<>();
        while (scan.hasNextLine()){
            String str = scan.nextLine();
            for (String numb:str.split(" ")){
                list.add( Float.parseFloat(numb));
            }
        }
        scan.close();
        fileReader.close();
        return list;
    }

}
