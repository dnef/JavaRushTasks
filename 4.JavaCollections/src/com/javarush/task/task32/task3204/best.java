package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

public class best {

/*
Генератор паролей
*/

//    public class Solution {
//        public static void main(String[] args) {
//            ByteArrayOutputStream password = getPassword();
//            System.out.println(password.toString());
//        }
//
//        public static ByteArrayOutputStream getPassword() {
//            //incorrect generation
//            ByteArrayOutputStream is = new ByteArrayOutputStream();
//            Basket[] baskets = new Basket[3];
//            baskets[0] = new Basket('0', '9');
//            baskets[1] = new Basket('a', 'z');
//            baskets[2] = new Basket('A', 'Z');
//            int i = 0;
//            for (; i < 5; i++) {
//                int index = (int) (Math.random() * 3);
//                generateChar(is, baskets, index);
//            }
//            for (int j = 0; j < baskets.length; j++) {
//                if (!baskets[j].isSelected()) {
//                    generateChar(is, baskets, j);
//                    i++;
//                }
//            }
//
//            while (i < 8) {
//                int index = (int) (Math.random() * 3);
//                generateChar(is, baskets, index);
//                i++;
//            }
//            return is;
//        }
//
//        private static void generateChar(ByteArrayOutputStream is, Basket[] baskets, int index) {
//            Basket basket = baskets[index];
//            is.write((char) basket.getRandom());
//        }
//
//        public static class Basket {
//            int begin;
//            int quantity;
//            boolean selected;
//
//            private Basket(char begin, char end) {
//                this.begin = begin;
//                this.quantity = end - begin;
//            }
//
//            public int getRandom() {
//                return (int) (Math.random() * quantity) + begin;
//            }
//
//            public boolean isSelected() {
//                return selected;
//            }
//        }
//    }

}
