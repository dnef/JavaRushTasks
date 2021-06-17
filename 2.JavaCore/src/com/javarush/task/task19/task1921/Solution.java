package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader readerLine = new BufferedReader(new FileReader(args[0]));
        //SimpleDateFormat format = new SimpleDateFormat("dd MМ yyyy");

        int n[] = new int[3];
        String line;
        while (readerLine.ready()) {
            int d = 2;
            String str = "";
            line= readerLine.readLine();
            String[] s = line.split(" ");
            for (int i = s.length - 1; i >= 0; i--) {
                try {
                    n[d] = Integer.parseInt(s[i]);
                    d--;
                } catch (NumberFormatException | NullPointerException ex) {
                    str = s[i] + " " + str;
                }
            }
            str=str.trim();
            Date birthDay = new GregorianCalendar(n[2],n[1]-1,n[0]).getTime();
            Person person = new Person(str, birthDay);
            PEOPLE.add(person);
            //System.out.println(n[0]+" "+n[1]+" "+n[2]);
            //System.out.println(str);
        }
        readerLine.close();
        for (Person person:PEOPLE){
            System.out.println(person.getBirthDate());
        }
    }
}
