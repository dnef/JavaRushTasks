package com.javarush.task.task17.task1711;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
CRUD
*/

public class Solution {
    public volatile static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        Person person = null;
        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i = i + 3) {
                        if (args[i + 1].equals("м")) {
                            person = Person.createMale(args[i], new SimpleDateFormat("dd/MM/yyyy").parse(args[i + 2]));
                        }
                        if (args[i + 1].equals("ж")) {
                            person = Person.createFemale(args[i], new SimpleDateFormat("dd/MM/yyyy").parse(args[i + 2]));
                        }
                        allPeople.add(person);
                        System.out.println(allPeople.indexOf(person));
                    }
                }
                break;
            case "-u":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i = i + 4) {
                        allPeople.get(Integer.parseInt(args[i])).setName(args[i + 1]);
                        allPeople.get(Integer.parseInt(args[i])).setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse(args[i + 3]));
                        if (args[i + 2].equals("м")) {
                            allPeople.get(Integer.parseInt(args[i])).setSex(Sex.MALE);
                        }
                        if (args[i + 2].equals("ж")) {
                            allPeople.get(Integer.parseInt(args[i])).setSex(Sex.FEMALE);
                        }
                    }
                }
                break;
            case "-d":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        allPeople.get(Integer.parseInt(args[i])).setName(null);
                        allPeople.get(Integer.parseInt(args[i])).setBirthDate(null);
                        allPeople.get(Integer.parseInt(args[i])).setSex(null);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        String name = allPeople.get(Integer.parseInt(args[i])).getName();
                        String sex = null;
                        Date date = allPeople.get(Integer.parseInt(args[i])).getBirthDate();
                        if (allPeople.get(Integer.parseInt(args[i])).getSex() == Sex.MALE) {
                            sex = "м";
                        }
                        if (allPeople.get(Integer.parseInt(args[i])).getSex() == Sex.FEMALE) {
                            sex = "ж";
                        }
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                        String dateF = simpleDateFormat.format(date);
                        System.out.println(name + " " + sex + " " + dateF);
                    }
                }
                break;


        }
    }
}
