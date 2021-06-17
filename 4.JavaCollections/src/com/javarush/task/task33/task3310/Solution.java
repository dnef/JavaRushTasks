package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;
import com.javarush.task.task33.task3310.tests.FunctionalTest;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
//        System.out.println("Stack trace:");
//        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
//        for (int i = 1; i < stackTraces.length; i++) {
//            System.out.println(stackTraces[i]);
//        }
        StorageStrategy storageStrategyJDBC = new JdbcStrategy();
        testStrategy(storageStrategyJDBC,10);
        StorageStrategy storageStrategy = new HashMapStorageStrategy();
        testStrategy(storageStrategy,10000);
        StorageStrategy oursStrategy = new OurHashMapStorageStrategy();
        testStrategy(oursStrategy,10000);
        StorageStrategy fileStrategy = new FileStorageStrategy();
        testStrategy(fileStrategy,10);
        StorageStrategy biStrategi = new OurHashBiMapStorageStrategy();
        testStrategy(biStrategi,10000);
        StorageStrategy guavaMap = new HashBiMapStorageStrategy();
        testStrategy(guavaMap,10000);
        StorageStrategy apacheBiDi = new DualHashBidiMapStorageStrategy();
        testStrategy(apacheBiDi,10000);

    }
    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> keys = new HashSet<>();
        for (String str:strings){
            keys.add(shortener.getId(str));
        }
        return keys;
    }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strings = new HashSet<>();
        for (Long key:keys){
            strings.add(shortener.getString(key));
        }
        return strings;
    }
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){

        Set<String> stringsTests = new HashSet<>();
        Helper.printMessage(strategy.getClass().getSimpleName());
        for (int i=0 ;i<elementsNumber;i++){
            stringsTests.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Set<Long> ids;
        Date startDate = new Date();
        ids=getIds(shortener,stringsTests);
        Date stopDate = new Date();
        Long time=stopDate.getTime() - startDate.getTime();
        Helper.printMessage(time.toString());

        startDate = new Date();
        Set<String>strings =getStrings(shortener,ids);
        stopDate = new Date();
        Helper.printMessage(Long.toString(stopDate.getTime() - startDate.getTime()));
        if (stringsTests.equals(strings)){
            Helper.printMessage("Тест пройден.");
        }else {Helper.printMessage("Тест не пройден.");}
        //Чистим базу после проверки
        if (strategy.getClass().equals(JdbcStrategy.class)) {
            JdbcStrategy jdbcStrategy = new JdbcStrategy();
            jdbcStrategy.DeleteRowAll();
        }
    }
}
