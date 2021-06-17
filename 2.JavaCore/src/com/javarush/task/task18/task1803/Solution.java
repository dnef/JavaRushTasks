package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        int[] bute = new int[256];
        FileInputStream readFile = new FileInputStream(file);

        while (readFile.available()>0) {
            bute[readFile.read()] += 1;
        }
        readFile.close();
        int max=0;
        for(int i=0;i<bute.length;i++){
            if(bute[i]>max){
                max=bute[i];
            }
        }
        for(int i=0;i<bute.length;i++){
            if(bute[i]==max){
                System.out.println(i);
            }
        }

    }
}
