package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Проход по дереву файлов
*/

public class Solution {
    public static void main(String[] args) {
        TreeSet<File> treeSet = new TreeSet<>();
        TreeMap<String,File> treeMap = new TreeMap<>();
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allContent = new File(resultFileAbsolutePath.getParent()+"/allFilesContent.txt");
        if (allContent.exists()) {
            FileUtils.deleteFile(allContent);
        }
        if(FileUtils.isExist(allContent)) {
            FileUtils.renameFile(resultFileAbsolutePath, allContent);
        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(allContent,true)){
            Stack stack = new Stack();
            stack.push(path);
            while (!stack.empty()){
                File dirFile = (File) stack.pop();
                if (dirFile.isDirectory()){
                    for (File file:dirFile.listFiles()){
                        stack.push(file);
                    }
                }
                if (dirFile.isFile() && !dirFile.equals(allContent)){
                    if (dirFile.length()<=50) {
                        treeSet.add(dirFile);
                    }
                }
            }
            for (File file:treeSet){
                treeMap.put(file.getName(),file);
                System.out.println(file.getAbsoluteFile());
            }
            for (Map.Entry<String,File> entry:treeMap.entrySet()){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(entry.getValue()));
                String str;
                while ((str= bufferedReader.readLine())!=null){
                    fileOutputStream.write(str.getBytes());
                    str="";
                }
                fileOutputStream.write("\n".getBytes());
                bufferedReader.close();
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
