package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/* 
Прайсы 2
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String fileName = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        fileName = reader.readLine();
        //fileName = "d://temp.txt";
        reader.close();
        LinkedHashMap<Integer, String> hashMap = writeHashFile(fileName);

        if(args.length ==5 && args[0].equals("-u") && args[1] !=null && args[2] != null && args[3] != null) {
            hashMap = editHashFile(hashMap, args);
            fileWrite(hashMap,fileName);
        }
        if (args.length == 2 && args[0].equals("-d")){
            hashMap = delHashFile(hashMap,Integer.parseInt(args[1]));
            fileWrite(hashMap,fileName);
        }




    }

    private static void fileWrite(LinkedHashMap<Integer, String> hashMap, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        hashMap.forEach((k,v)-> {
            try {
                fileWriter.write(v+'\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileWriter.close();
    }

    private static LinkedHashMap<Integer, String> delHashFile(LinkedHashMap<Integer, String> hashMap, int key) {
        hashMap.remove(key);
        return hashMap;
    }

    private static LinkedHashMap<Integer, String> editHashFile(LinkedHashMap<Integer, String> hashMap, String[] args) {
        int spaсe=0;
        String idStr = null;
        String productName = null;
        String price = null;
        String quantity = null;
        String addStr = null;

        spaсe = 8;
        idStr = padRight(args[1],spaсe);

        spaсe = 30;
        productName = padRight(args[2],spaсe);

        spaсe = 8;
        price = padRight(args[3],spaсe);

        spaсe = 4;
        quantity = padRight(args[4],spaсe);
        addStr = idStr+productName+price+quantity;
        hashMap.put(Integer.parseInt(args[1]),addStr);
        return hashMap;
    }

    private static LinkedHashMap writeHashFile(String fileName) {
        LinkedHashMap<Integer, String> fileHash = new LinkedHashMap<>();
        String str;
        int id = 0;
        try {
           BufferedReader lineFile = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"windows-1251"));
            //FileReader использует кодировку по умолчанию
           // BufferedReader lineFile = new BufferedReader(new FileReader(fileName));
            while ((str = lineFile.readLine()) !=null){
                id = Integer.parseInt(str.substring(0,8).trim());
                fileHash.put(id,str);
            }
            lineFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileHash;
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }


}

