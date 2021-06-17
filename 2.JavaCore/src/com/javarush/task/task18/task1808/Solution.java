package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) {
        String nameFile1=null;
        String nameFile2 = null;
        String nameFile3 = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            nameFile1 = reader.readLine();
            nameFile2 = reader.readLine();
            nameFile3 = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {

            FileInputStream fileInputStream = new FileInputStream(nameFile1);
            FileOutputStream fileOutputStream = new FileOutputStream(nameFile2);
            FileOutputStream fileOutputStream2 = new FileOutputStream(nameFile3);

            byte[] buff = new byte[fileInputStream.available()];
            fileInputStream.read(buff);
            fileInputStream.close();
            if(buff.length%2==0){
                fileOutputStream.write(buff,0,buff.length/2);
                fileOutputStream2.write(buff,buff.length/2,buff.length/2);
            }else {
                fileOutputStream.write(buff,0, (buff.length/2)+1);
                fileOutputStream2.write(buff,(buff.length/2)+1, buff.length/2);
            }
            fileOutputStream2.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
