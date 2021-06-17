package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);
    static {
        threads.add(new Thread(new Th1()));
        threads.add(new Thread(new Th2()));
        threads.add(new Thread(new Th3()));
        threads.add(new Th4());
        threads.add(new Thread(new Th5()));
    }

    public static void main(String[] args) {
    }
    public static class Th1 implements Runnable{
        @Override
        public void run() {
            while (true){
            }

        }
    }
    public static class Th2 implements Runnable{
        @Override
        public void run() {
            try {
                while (true){Thread.sleep(0);}
            }catch (InterruptedException ex){
                System.out.println("InterruptedException"); return;}
        }
    }
    public static class Th3 implements Runnable{
        @Override
        public void run() {
            while (true){
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace(); return;
                }

            }
        }
    }
    public static class Th4 extends Thread implements Message{
        @Override
        public void run() {
            while(!isInterrupted()){}
        }

        @Override
        public void showWarning() {
            interrupt();
        }
    }
    public static class Th5 implements Runnable{
        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str;
            int summ = 0;
            while(true){
                try {
                    if ((str=reader.readLine()).equals("N")) break;
                } catch (IOException e) {
                    e.printStackTrace();return;
                }
                summ=summ+Integer.parseInt(str);
            }
            System.out.println(summ);
        }
    }
}