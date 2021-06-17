package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String files;
        String part;
        Integer partN;
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!(files=reader.readLine()).equals("end")){
            part = files.substring(files.lastIndexOf(".part")+1);
            partN = Integer.parseInt(part.replace("part",""));
            treeMap.put(partN,files);
        }
        reader.close();
        String newFile = treeMap.get(1).substring(0,treeMap.get(1).lastIndexOf("."));
        File filedel = new File(newFile);
        filedel.delete();
        BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(newFile,true));
        //FileOutputStream fileOutputStream = new FileOutputStream(newFile,true);
        for (Map.Entry entry : treeMap.entrySet()){
            File file = new File((String) entry.getValue());
            //FileInputStream inputStream = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buff = new byte[inputStream.available()];
            inputStream.read(buff);
            inputStream.close();
            fileOutputStream.write(buff);

        }
        fileOutputStream.close();

    }
}
