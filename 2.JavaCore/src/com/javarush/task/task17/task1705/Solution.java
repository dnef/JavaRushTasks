package com.javarush.task.task17.task1705;

import java.util.ArrayList;
import java.util.List;

/* 
Сад-огород
*/

public class Solution {


    public static void main(String[] args) {

    }

    public static class Garden {

        public final List<String> fruits = new ArrayList<String>();
        public final List<String> vegetables = new ArrayList<String>();

            public void addFruit ( int index, String fruit) {
                synchronized (this) {
                    fruits.add(index, fruit);
                }
            }
            public void removeFruit ( int index) {
                synchronized (this) {
                    fruits.remove(index);
                }
            }
            public void addVegetable ( int index, String vegetable){
                synchronized (this) {
                    vegetables.add(index, vegetable);
                }
            }
            public void removeVegetable ( int index) {
                synchronized (this) {
                    vegetables.remove(index);
                }
            }

    }
}
