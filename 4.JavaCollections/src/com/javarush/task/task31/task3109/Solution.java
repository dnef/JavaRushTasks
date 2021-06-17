package com.javarush.task.task31.task3109;

import javax.xml.stream.XMLInputFactory;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();
        try {
        if (fileName.substring(fileName.lastIndexOf(".")+1).equals("xml")){
            properties.loadFromXML(new FileInputStream(fileName));
        }else {
            File file = new File(fileName);
            properties.load(new FileReader(file));
        }
            return properties;
        } catch (IOException e) {
            return properties;
        }
    }
}
