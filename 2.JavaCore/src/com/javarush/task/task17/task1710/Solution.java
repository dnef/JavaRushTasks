package com.javarush.task.task17.task1710;

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
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        Person person=null;
        switch (args[0]){
            case "-c" :
                if(args[2].equals("м")){
                    person = Person.createMale(args[1],new SimpleDateFormat("dd/MM/yyyy").parse(args[3]));
                }
                if(args[2].equals("ж")){
                    person = Person.createFemale(args[1],new SimpleDateFormat("dd/MM/yyyy").parse(args[3]));
                }
                allPeople.add(person);
                System.out.println(allPeople.indexOf(person));
                break;
            case "-u" :
                allPeople.get(Integer.parseInt(args[1])).setName(args[2]);
                allPeople.get(Integer.parseInt(args[1])).setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse(args[4]));
                if (args[3].equals("м")) {
                    allPeople.get(Integer.parseInt(args[1])).setSex(Sex.MALE);
                }
                if (args[3].equals("ж")) {
                    allPeople.get(Integer.parseInt(args[1])).setSex(Sex.FEMALE);
                }
                break;
            case "-d" :
                allPeople.get(Integer.parseInt(args[1])).setName(null);
                allPeople.get(Integer.parseInt(args[1])).setBirthDate(null);
                allPeople.get(Integer.parseInt(args[1])).setSex(null);
                break;
            case "-i" :
                String name = allPeople.get(Integer.parseInt(args[1])).getName();
                String sex="";
                Date date = allPeople.get(Integer.parseInt(args[1])).getBirthDate();
                if (allPeople.get(Integer.parseInt(args[1])).getSex() == Sex.MALE){
                    sex = "м";
                }
                if (allPeople.get(Integer.parseInt(args[1])).getSex() == Sex.FEMALE){
                    sex = "ж";
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                String dateF = simpleDateFormat.format(date);
                System.out.println(name+" "+sex+" "+dateF);
                break;


        }
    }
}
