package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/


public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse(132);
    }

    public void recurse(int n) {
        if (n <= 1) return;
        for (int i = 2; i > 0; i++) {
            if (n % i == 0) {
                System.out.println(i + "  ");
                n = n / i;
                recurse(n);
                break;
            }
        }
    }

}
