package com.javarush.task.task18.task1822;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        String fileName = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //fileName = reader.readLine();
        fileName = "d://temp.txt";
        reader.close();
        String strFind = find(fileName,args[0]);

    }
    private static String find(String fileName,String id) {
        String str=null;
        try {
            BufferedReader lineFile = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"windows-1251"));
            //FileReader использует кодировку по умолчанию
            // BufferedReader lineFile = new BufferedReader(new FileReader(fileName));
            while ((str = lineFile.readLine()) !=null){
                if (str.substring(0,str.indexOf(" ")).equals(id)){
                    System.out.println(str);
                }

            }
            lineFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
