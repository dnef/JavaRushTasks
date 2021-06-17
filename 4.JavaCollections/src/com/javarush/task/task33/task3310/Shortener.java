package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.JdbcStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {
    private Long lastId = 0L;
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string) {
        if(storageStrategy.containsValue(string)){
            return storageStrategy.getKey(string);
        }
        lastId=lastId+1L;
        if (storageStrategy.getClass().equals(JdbcStrategy.class)){
            lastId = Long.valueOf(string.hashCode());
        }
        storageStrategy.put(lastId,string);
        return lastId;
    }

    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
