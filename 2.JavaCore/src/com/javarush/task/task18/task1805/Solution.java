package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        int[] bute = new int[256];
        int[] mass = new int[256];
        FileInputStream readFile = new FileInputStream(file);

        while (readFile.available()>0) {
            int n =readFile.read();
            bute[n] = n;
        }
        readFile.close();
        int k=0;
         for(int i=0;i<bute.length;i++){
           if(bute[i]!=0){
               mass[k] = i;
               k++;
          }
         }
        for(int i=0;i<mass.length;i++){
            if(mass[i] !=0){
                System.out.print(mass[i]+" ");
            }
        }

    }
}