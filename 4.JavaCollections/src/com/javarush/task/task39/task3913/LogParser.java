package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.logs.Log;
import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    private Date stringForDate(String dateLine) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateLine);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private List<Log> logList() {
        List<Log> logs = new ArrayList<>();
        try {
            List<Path> filesInFolder = Files.find(logDir, Integer.MAX_VALUE, (p, a) -> String.valueOf(p).endsWith(".log")).collect(Collectors.toList());
            for (Path file : filesInFolder) {
                FileReader fileReader = new FileReader(file.toFile());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] arrLine = line.split("\t");
                    Date date = stringForDate(arrLine[2]);
                    String event = null;
                    Integer eventNumber = null;
                    if (arrLine[3].indexOf(' ') == -1) {
                        event = arrLine[3];
                        eventNumber = null;
                    } else {
                        String[] eventArr = arrLine[3].split(" ");
                        event = eventArr[0];
                        eventNumber = Integer.valueOf(eventArr[1]);
                    }
                    Log log = new Log(arrLine[0], arrLine[1], date, Event.valueOf(event), eventNumber, Status.valueOf(arrLine[4]));
                    logs.add(log);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logs;
    }

    public List<Log> sortDate(Date after, Date before) {
        List<Log> logDateSort = null;
        if (after != null && before != null) {
            if (after.compareTo(before) == 0) {
                logDateSort = logList().stream().filter(p -> p.getDate().equals(after)).collect(Collectors.toList());
            } else {
                logDateSort = logList().stream().filter(p -> p.getDate().after(after)).filter(p -> p.getDate().before(before)).collect(Collectors.toList());
            }
        }
        if (after == null && before != null) {
            logDateSort = logList().stream().filter(p -> p.getDate().before(before)).collect(Collectors.toList());
        }
        if (before == null && after != null) {
            logDateSort = logList().stream().filter(p -> p.getDate().after(after)).collect(Collectors.toList());
        }
        if (before == null && after == null) {
            logDateSort = logList();
        }

        return logDateSort;

    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before);
        HashSet<String> uniqueIp = new HashSet<>();
        for (Log log : logDateSort) {
            uniqueIp.add(log.getIp());
        }

        return uniqueIp.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before);
        HashSet<String> uniqueIp = new HashSet<>();
        for (Log log : logDateSort) {
            uniqueIp.add(log.getIp());
        }
        return uniqueIp;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before);
        List<Log> userList = logDateSort.stream().filter(p -> p.getUser().equals(user)).collect(Collectors.toList());
        HashSet<String> userIp = new HashSet<>();
        for (Log log : userList) {
            userIp.add(log.getIp());
        }
        return userIp;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before);
        List<Log> eventList = logDateSort.stream().filter(p -> p.getEvent().equals(event)).collect(Collectors.toList());
        HashSet<String> eventIp = new HashSet<>();
        for (Log log : eventList) {
            eventIp.add(log.getIp());
        }
        return eventIp;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before);
        List<Log> statusList = logDateSort.stream().filter(p -> p.getStatus().equals(status)).collect(Collectors.toList());
        HashSet<String> statusIp = new HashSet<>();
        for (Log log : statusList) {
            statusIp.add(log.getIp());
        }
        return statusIp;
    }

    //////////////////////////////////////// 2 задача
    @Override
    public Set<String> getAllUsers() {
        List<Log> logDateSort = sortDate(null, null);
        Set<String> users = new HashSet<>();
        for (Log log : logDateSort) {
            users.add(log.getUser());
        }
        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before);
        Set<String> users = new HashSet<>();
        for (Log log : logDateSort) {
            users.add(log.getUser());
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        List<Log> logDateSortUser = sortDate(after, before).stream().filter(p -> p.getUser().equals(user)).collect(Collectors.toList());
        Set<Event> userEvents = new HashSet<>();
        for (Log log : logDateSortUser) {
            userEvents.add(log.getEvent());
        }
        return userEvents.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        List<Log> logDateSortIp = sortDate(after, before).stream().filter(p -> p.getIp().equals(ip)).collect(Collectors.toList());
        Set<String> userEventsIp = new HashSet<>();
        for (Log log : logDateSortIp) {
            userEventsIp.add(log.getUser());
        }
        return userEventsIp;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.LOGIN)).collect(Collectors.toList());
        Set<String> userLogin = new HashSet<>();
        for (Log log : logDateSort) {
            userLogin.add(log.getUser());
        }
        return userLogin;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.DOWNLOAD_PLUGIN)).collect(Collectors.toList());
        Set<String> userPlugin = new HashSet<>();
        for (Log log : logDateSort) {
            userPlugin.add(log.getUser());
        }
        return userPlugin;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.WRITE_MESSAGE)).collect(Collectors.toList());
        Set<String> userMessage = new HashSet<>();
        for (Log log : logDateSort) {
            userMessage.add(log.getUser());
        }
        return userMessage;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.SOLVE_TASK)).collect(Collectors.toList());
        Set<String> userTask = new HashSet<>();
        for (Log log : logDateSort) {
            userTask.add(log.getUser());
        }
        return userTask;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        List<Log> logDateSort = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.SOLVE_TASK)).filter(p -> p.getEventNumb() == task).collect(Collectors.toList());
        Set<String> userTask = new HashSet<>();
        for (Log log : logDateSort) {
            userTask.add(log.getUser());
        }
        return userTask;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        List<Log> logDateSort = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.DONE_TASK)).collect(Collectors.toList());
        Set<String> userTask = new HashSet<>();
        for (Log log : logDateSort) {
            userTask.add(log.getUser());
        }
        return userTask;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        List<Log> logDateSort = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.DONE_TASK)).filter(p -> p.getEventNumb() == task).collect(Collectors.toList());
        Set<String> userTask = new HashSet<>();
        for (Log log : logDateSort) {
            userTask.add(log.getUser());
        }
        return userTask;
    }

    ///////////////////////////////////////////3 задание
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        List<Log> logDateSortUserEvent = sortDate(after, before).stream().filter(p -> p.getUser().equals(user)).filter(p -> p.getEvent().equals(event)).collect(Collectors.toList());
        Set<Date> dates = new HashSet<>();
        for (Log log : logDateSortUserEvent) {
            dates.add(log.getDate());
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> logFalseDate = sortDate(after, before).stream().filter(p -> p.getStatus().equals(Status.FAILED)).map(m -> m.getDate()).collect(Collectors.toSet());
        return logFalseDate;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> logErrorDate = sortDate(after, before).stream().filter(p -> p.getStatus().equals(Status.ERROR)).map(m -> m.getDate()).collect(Collectors.toSet());
        return logErrorDate;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> dates;
        dates = sortDate(after, before).stream().filter(p -> p.getUser().equals(user) && p.getEvent().equals(Event.LOGIN)).filter(p -> p.getStatus().equals(Status.OK))
                .map(log -> log.getDate()).collect(Collectors.toSet());
        if (dates.size() == 0) {
            return null;
        }
        Date minDate = dates.iterator().next();
        for (Date date : dates) {
            if (date.getTime() < minDate.getTime()) {
                minDate = date;
            }
        }
        return minDate;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date> dates = sortDate(after, before).stream().
                filter(p -> p.getUser().equals(user)).
                filter(p -> p.getEvent().equals(Event.SOLVE_TASK)).
                filter(p -> p.getEventNumb() == task).
                map(log -> log.getDate()).collect(Collectors.toSet());

        if (dates.size() == 0) {
            return null;
        }
        Date minDate = dates.iterator().next();
        for (Date date : dates) {
            if (date.getTime() < minDate.getTime()) {
                minDate = date;
            }
        }
        return minDate;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date> dates = sortDate(after, before).stream().
                filter(p -> p.getUser().equals(user)).
                filter(p -> p.getEvent().equals(Event.DONE_TASK)).
                filter(p -> p.getEventNumb() == task).
                map(log -> log.getDate()).collect(Collectors.toSet());

        if (dates.size() == 0) {
            return null;
        }
        Date minDate = dates.iterator().next();
        for (Date date : dates) {
            if (date.getTime() < minDate.getTime()) {
                minDate = date;
            }
        }
        return minDate;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> dates = sortDate(after, before).stream().filter(p -> p.getUser().equals(user) && p.getEvent().equals(Event.WRITE_MESSAGE))
                .map(log -> log.getDate()).collect(Collectors.toSet());
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> dates = sortDate(after, before).stream().filter(p -> p.getUser().equals(user) && p.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                .map(log -> log.getDate()).collect(Collectors.toSet());
        return dates;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        Set<Event> logSet = sortDate(after, before).stream().map(log -> log.getEvent()).collect(Collectors.toSet());
        return logSet.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> logSet = sortDate(after, before).stream().map(log -> log.getEvent()).collect(Collectors.toSet());
        return logSet;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> logSet = sortDate(after, before).stream().filter(p -> p.getIp().equals(ip)).map(log -> log.getEvent()).collect(Collectors.toSet());
        return logSet;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> logSet = sortDate(after, before).stream().filter(p -> p.getUser().equals(user)).map(log -> log.getEvent()).collect(Collectors.toSet());
        return logSet;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> logSet = sortDate(after, before).stream().filter(p -> p.getStatus().equals(Status.FAILED)).map(log -> log.getEvent()).collect(Collectors.toSet());
        return logSet;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> logSet = sortDate(after, before).stream().filter(p -> p.getStatus().equals(Status.ERROR)).map(log -> log.getEvent()).collect(Collectors.toSet());
        return logSet;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        List<Event> logSet = sortDate(after, before).stream()
                .filter(p -> p.getEvent().equals(Event.SOLVE_TASK))
                .filter(p -> p.getEventNumb() == task)
                .map(log -> log.getEvent()).collect(Collectors.toList());
        return logSet.size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        List<Event> logSet = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.DONE_TASK))
                .filter(p -> p.getEventNumb() == task)
                //.filter(p->p.getStatus().equals(Status.OK))
                .map(log -> log.getEvent()).collect(Collectors.toList());
        return logSet.size();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> mapSolvedTask = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.SOLVE_TASK)).collect(Collectors.toMap(Log::getEventNumb, log -> 1, Integer::sum));
        return mapSolvedTask;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> mapSolvedTask = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.DONE_TASK))
                .filter(p -> p.getEventNumb() > 0)
                .collect(Collectors.toMap(Log::getEventNumb, log -> 1, Integer::sum));
        if (mapSolvedTask.size() == 0) {
            return new HashMap<>();
        }
        return mapSolvedTask;
    }

    @Override
    public Set<Object> execute(String query) {
        String field1;
        String field2 = null;
        String value1 = null;
        Date after = null;
        Date before = null;
        Pattern pattern = Pattern.compile("get (ip|user|date|event|status)"
                + "( for (ip|user|date|event|status) = \"(.*?)\")?" + "( and date between \"(.*?)\" and \"(.*?)\")?");
        Matcher matcher = pattern.matcher(query);
        matcher.find();
        field1 = matcher.group(1);
        if (matcher.group(2) != null) {
            field2 = matcher.group(3);
            value1 = matcher.group(4);
            if (matcher.group(5) != null) {
                    after = stringForDate(matcher.group(6));
                    before = stringForDate(matcher.group(7));
            }
        }
        switch (field1) {
            case ("ip"):
                if (field2 != null && value1 != null) {
                    return getListForField(after,before,field2, value1).stream().map(Log::getIp).collect(Collectors.toSet());
                } else {
                    return sortDate(null, null).stream().map(Log::getIp).collect(Collectors.toSet());
                }
            case ("user"):
                if (field2 != null && value1 != null) {
                    return getListForField(after,before,field2, value1).stream().map(Log::getUser).collect(Collectors.toSet());
                } else {
                    return sortDate(null, null).stream().map(Log::getUser).collect(Collectors.toSet());
                }
            case ("date"):
                if (field2 != null && value1 != null) {
                    return getListForField(after,before,field2, value1).stream().map(Log::getDate).collect(Collectors.toSet());
                } else {
                    return sortDate(null, null).stream().map(Log::getDate).collect(Collectors.toSet());
                }
            case ("event"):
                if (field2 != null && value1 != null) {
                    return getListForField(after,before,field2, value1).stream().map(Log::getEvent).collect(Collectors.toSet());
                } else {
                    return sortDate(null, null).stream().map(Log::getEvent).collect(Collectors.toSet());
                }
            case ("status"):
                if (field2 != null && value1 != null) {
                    return getListForField(after,before,field2, value1).stream().map(Log::getStatus).collect(Collectors.toSet());
                } else {
                    return sortDate(null, null).stream().map(Log::getStatus).collect(Collectors.toSet());
                }
        }
        return null;
    }

    public List<Log> getListForField(Date after,Date before,String field2, String value) {
        List<Log> logList = null;
        switch (field2) {
            case ("ip") : logList = sortDate(after, before).stream().filter(p -> p.getIp().equals(value)).collect(Collectors.toList());
                break;
            case ("user"):
                logList = sortDate(after, before).stream().filter(p -> p.getUser().equals(value)).collect(Collectors.toList());
                break;
            case ("date"):
                //logList = sortDate(stringForDate(value), stringForDate(value)).stream().collect(Collectors.toList());
                logList = sortDate(new Date(after.getTime()+1L), new Date(before.getTime()-1L))
                        .stream().filter(p->p.getDate().equals(stringForDate(value)))
                        .collect(Collectors.toList());
                break;
            case ("event"):
                logList = sortDate(after, before).stream().filter(p -> p.getEvent().equals(Event.valueOf(value))).collect(Collectors.toList());
                break;
            case ("status"):
                logList = sortDate(after, before).stream().filter(p -> p.getStatus().equals(Status.valueOf(value))).collect(Collectors.toList());
                break;
        }
        return logList;
    }
}