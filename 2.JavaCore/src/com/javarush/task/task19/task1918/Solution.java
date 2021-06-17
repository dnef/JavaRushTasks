package com.javarush.task.task19.task1918;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;


import java.io.*;
import java.util.ArrayList;
import org.jsoup.select.Elements;


/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        reader.close();
        String str ="";
        while (fileReader.ready()){
            str = str+Character.toString((char) fileReader.read());

        }
        fileReader.close();
        Document document = Jsoup.parse(str,"", Parser.xmlParser());
        Elements elements = document.select(args[0]);
        for (Element element:elements){
            System.out.println(element);
        }
        }
    }
