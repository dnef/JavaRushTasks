package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        Date date = new Date();
        try {
            date = new SimpleDateFormat( "dd.MM.yy HH:mm:ss" ).parse( "28.12.2016 00:01:01" );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("кол-во уникальных IP за период");
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getNumberOfUniqueIPs(date, null));
        System.out.println(logParser.getNumberOfUniqueIPs(date, new Date()));
        System.out.println(logParser.getNumberOfUniqueIPs(null, null));

        System.out.println("Не повторяющиеся IP за период");
        List<String> listUniqueIp = logParser.getUniqueIPs(null, new Date()).stream().collect(Collectors.toList());
        listUniqueIp.forEach(System.out::println);

        System.out.println("UserIp: Eduard Petrovich Morozko");
        logParser.getIPsForUser("Eduard Petrovich Morozko",null, new Date()).forEach(System.out::println);

        System.out.println("Event: DOWNLOAD_PLUGIN");
        logParser.getIPsForEvent(Event.DOWNLOAD_PLUGIN,null, new Date()).forEach(System.out::println);

        System.out.println("Status: FAILED");
        logParser.getIPsForStatus(Status.FAILED,null, new Date()).forEach(System.out::println);

        System.out.println("All Users:");
        logParser.getAllUsers().forEach(System.out::println);

        System.out.println("All unique Users quantity");
        System.out.println(logParser.getNumberOfUsers(null,new Date()));

        System.out.println("getNumberOfUserEvents:");
        System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko",null,new Date()));

        System.out.println("getUsersForIP:");
        logParser.getUsersForIP("127.0.0.1",null,new Date()).forEach(System.out::println);

        System.out.println("getLoggedUsers:");
        logParser.getLoggedUsers(null,null).forEach(System.out::println);

        System.out.println("getDownloadedPluginUsers:");
        logParser.getDownloadedPluginUsers(null,null).forEach(System.out::println);

        System.out.println("getWroteMessageUsers:");
        logParser.getWroteMessageUsers(null,null).forEach(System.out::println);

        System.out.println("getSolvedTaskUsers:");
        logParser.getSolvedTaskUsers(null,null).forEach(System.out::println);

        System.out.println(" getSolvedTaskUsers 18:");
        logParser. getSolvedTaskUsers(null,null,18).forEach(System.out::println);

        System.out.println("getDoneTaskUsers:");
        logParser.getDoneTaskUsers(null,null).forEach(System.out::println);

        System.out.println("getDoneTaskUsers 15:");
        logParser. getDoneTaskUsers(null,null,15).forEach(System.out::println);

        System.out.println("getDatesForUserAndEvent:");
        logParser.getDatesForUserAndEvent("Eduard Petrovich Morozko",Event.WRITE_MESSAGE,null,new Date()).forEach(System.out::println);

        System.out.println("getDatesWhenSomethingFailed:");
        logParser.getDatesWhenSomethingFailed(null,new Date()).forEach(System.out::println);

        System.out.println("getDatesWhenErrorHappened:");
        logParser.getDatesWhenErrorHappened(null,new Date()).forEach(System.out::println);

        System.out.println("getDateWhenUserLoggedFirstTime:");
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Vasya Pupkin",null,null));

        System.out.println("getDateWhenUserSolvedTask:");
        System.out.println(logParser.getDateWhenUserSolvedTask("Vasya Pupkin",18,null,new Date()));

        System.out.println("getDateWhenUserDoneTask:");
        System.out.println(logParser.getDateWhenUserDoneTask("Vasya Pupkin",15,null,new Date()));

        System.out.println("getDatesWhenUserWroteMessage:");
        logParser.getDatesWhenUserWroteMessage("Eduard Petrovich Morozko",null,null).forEach(System.out::println);

        System.out.println("getDatesWhenUserDownloadedPlugin:");
        logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko",null,null).forEach(System.out::println);
///////////////////////////////////////////////4 задача
        System.out.println("getNumberOfAllEvents:");
        System.out.println(logParser.getNumberOfAllEvents(null,null));

        System.out.println("getAllEvents:");
        logParser.getAllEvents(null,null).forEach(System.out::println);

        System.out.println("getEventsForIP:");
        logParser.getEventsForIP("127.0.0.1",null,null).forEach(System.out::println);

        System.out.println("getEventsForUser:");
        logParser.getEventsForUser("Eduard Petrovich Morozko",null,null).forEach(System.out::println);

        System.out.println("getFailedEvents:");
        logParser.getFailedEvents(null,null).forEach(System.out::println);

        System.out.println("getErrorEvents:");
        logParser.getErrorEvents(null,null).forEach(System.out::println);

        System.out.println("getNumberOfAttemptToSolveTask:");
        System.out.println(logParser.getNumberOfAttemptToSolveTask(18,null,null));

        System.out.println("getNumberOfSuccessfulAttemptToSolveTask:");
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(15,null,null));

        System.out.println("getAllSolvedTasksAndTheirNumber:");
        logParser.getAllSolvedTasksAndTheirNumber(null,null).forEach((k,v)-> System.out.println(k+" "+v));

        System.out.println("getAllDoneTasksAndTheirNumber:");
        logParser.getAllDoneTasksAndTheirNumber(null,null).forEach((k,v)-> System.out.println(k+" "+v));
///////////////////////////////////////////////5 задача
        System.out.println("execute(\"get ip\"):");
        logParser.execute("get ip").forEach(System.out::println);
        System.out.println("execute(\"get user\"):");
        logParser.execute("get user").forEach(System.out::println);
        System.out.println("execute(\"get date\"):");
        logParser.execute("get date").forEach(System.out::println);
        System.out.println("execute(\"get event\"):");
        logParser.execute("get event").forEach(System.out::println);
        System.out.println("execute(\"get status\"):");
        logParser.execute("get status").forEach(System.out::println);
        ///////////////////////////////////////////////6 задача
        System.out.println("ececute(\"get ip for user = \"Vasya Pupkin\"\")");
        logParser.execute("get ip for user = \"Vasya Pupkin\" ").forEach(System.out::println);
        System.out.println("ececute(\"get ip for date = \"13.09.2013 5:04:50\"\")");
        logParser.execute("get ip for date = \"13.09.2013 5:04:50\"").forEach(System.out::println);
        ///////////////////////////////////////////////7 задача

    }
}