package com.javarush.task.task19.task1904;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.*;
import java.util.*;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException {
    }

    public static class PersonScannerAdapter implements PersonScanner {
        Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String line = fileScanner.nextLine();
            String [] data = line.split(" ",4);
            Date date = null;
            DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
            try {
                date = dateFormat.parse(data[3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Person person = new Person(data[1],data[2],data[0],date);

            return person;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();

        }
    }
}
