package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        BufferedReader readF1 = new BufferedReader(new FileReader(file1));
        BufferedReader readF2 = new BufferedReader(new FileReader(file2));
        ArrayList<String> listFile1 = new ArrayList<>();
        ArrayList<String> listFile2 = new ArrayList<>();

        while (readF1.ready()){
            listFile1.add(readF1.readLine());
        }
        while (readF2.ready()){
            listFile2.add(readF2.readLine());
        }
        readF1.close();
        readF2.close();

        while (!listFile1.isEmpty() | !listFile2.isEmpty()){
            if(listFile2.isEmpty()){
                lines.add(new LineItem(Type.REMOVED,listFile1.get(0)));
                listFile1.remove(0);
            }
            else if (listFile1.isEmpty()){
                lines.add(new LineItem(Type.ADDED,listFile2.get(0)));
                listFile2.remove(0);
            }
            else if (listFile1.get(0).equals(listFile2.get(0))){
                lines.add(new LineItem(Type.SAME,listFile1.get(0)));
                listFile1.remove(0);
                listFile2.remove(0);
            }
            else if (listFile1.get(0).equals(listFile2.get(1))){
                lines.add(new LineItem(Type.ADDED,listFile2.get(0)));
                listFile2.remove(0);
            }
            else {lines.add(new LineItem(Type.REMOVED,listFile1.get(0)));
            listFile1.remove(0);
            }
        }
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
