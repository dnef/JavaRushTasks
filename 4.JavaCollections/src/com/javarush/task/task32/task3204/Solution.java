package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

/*
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        final char[] lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        final char[] uppercase = "ABCDEFGJKLMNPRSTUVWXYZ".toCharArray();
        final char[] numbers = "0123456789".toCharArray();
        final char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789".toCharArray();
        Random random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.setLength(8);
        for (int i=0;i<5;i++){
            stringBuilder.append(allAllowed[random.nextInt(allAllowed.length)]);
        }
        stringBuilder.insert(random.nextInt(stringBuilder.length()), lowercase[random.nextInt(lowercase.length)]);
        stringBuilder.insert(random.nextInt(stringBuilder.length()), uppercase[random.nextInt(uppercase.length)]);
        stringBuilder.insert(random.nextInt(stringBuilder.length()), numbers[random.nextInt(numbers.length)]);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = stringBuilder.toString().getBytes();
        byteArrayOutputStream.write(buffer);
        return byteArrayOutputStream;
    }
}
