package com.javarush.task.task31.task3103;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/* 
Своя реализация
*/

public class Solution {
    public static byte[] readBytes(String fileName) throws IOException {
        //Path path = Paths.get(fileName);
        if (Files.isRegularFile(Paths.get(fileName))) {
            byte[] bytes = Files.readAllBytes(Paths.get(fileName));
            return bytes;
        }
        return null;
    }

    public static List<String> readLines(String fileName) throws IOException {
        //Path path = Paths.get(fileName);
        if (Files.isRegularFile(Paths.get(fileName))) {
            List<String> listStr = Files.readAllLines(Paths.get(fileName));
            return listStr;
        }
        return null;
    }

    public static void writeBytes(String fileName, byte[] bytes) throws IOException {
        //Path path = Paths.get(fileName);
        //if (Files.exists(Paths.get(fileName))){
            Files.write(Paths.get(fileName),bytes);
       // }
    }

    public static void copy(String resourceFileName, String destinationFileName) throws IOException {
//        Path pathRes = Paths.get(resourceFileName);
//        Path pathTarg = Paths.get(destinationFileName);
        if (Files.exists(Paths.get(resourceFileName))){
            Files.copy(Paths.get(resourceFileName),Paths.get(destinationFileName));
        }
    }
}
