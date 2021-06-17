package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Stack;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File path = new File(root);
        ArrayList<String> list = new ArrayList<>();
        Stack stack = new Stack();
        stack.push(path);
        while (!stack.empty()){
            File dirFile = (File) stack.pop();
            if (dirFile.isDirectory()){
                for (File file:dirFile.listFiles()){
                    stack.push(file);
                }
            }
            if (dirFile.isFile()){
                    list.add(dirFile.getAbsolutePath());
            }
        }
        return list;

    }

    public static void main(String[] args) throws IOException {
        System.out.println(getFileTree("c://temp"));

    }
}
