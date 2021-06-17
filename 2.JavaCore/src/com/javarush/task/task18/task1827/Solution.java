package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Прайсы
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String fileName = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //fileName = reader.readLine();
        fileName = "d://temp.txt";
        reader.close();
        FileWriter fileWriter = new FileWriter(fileName, true);

        int spaсe=0;
        String idStr = null;
        String productName = null;
        String price = null;
        String quantity = null;
        String strWrite = null;
        if(args.length ==4 && args[0].equals("-c") && args[1] !=null && args[2] != null && args[3] != null){
            int id = readFileId(fileName);
            spaсe = 8;
            idStr = padRight(Integer.toString(id),spaсe);

            spaсe = 30;
            productName = padRight(args[1],spaсe);

            spaсe = 8;
            price = padRight(args[2],spaсe);

            spaсe = 4;
            quantity = padRight(args[3],spaсe);
        }
        if (idStr != null && productName !=null && price != null && quantity != null){
            strWrite ='\n'+idStr+productName+price+quantity;
            fileWriter.write(strWrite);
        }
        fileWriter.close();

    }

    private static int readFileId(String fileName) {
        String str;
        //String end = null;
        int idMax = 0;
        int id = 0;
        try {
            BufferedReader lineFile = new BufferedReader(new FileReader(fileName));
            while ((str = lineFile.readLine()) !=null){
                System.out.println(str);
                id = Integer.parseInt(str.substring(0,8).trim());
                if(id>idMax){
                    idMax = id;
                }
            }
            lineFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        idMax=idMax+1;
        return idMax;
    }
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }


}
