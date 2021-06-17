package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

public class OurHashBiMapStorageStrategy implements StorageStrategy{
    HashMap<Long, String> k2v = new HashMap<>();
    HashMap<String, Long> v2k = new HashMap<>();
    //должен вернуть true, если хранилище содержит переданный ключ
    @Override
    public boolean containsKey(Long key) {

        return k2v.containsKey(key);
    }
    //должен вернуть true, если хранилище содержит переданное значение
    @Override
    public boolean containsValue(String value) {
        return v2k.containsKey(value);
    }
    //добавить в хранилище новую пару ключ - значение
    @Override
    public void put(Long key, String value) {
        k2v.put(key,value);
        v2k.put(value,key);
    }
    //вернуть ключ для переданного значения
    @Override
    public Long getKey(String value) {
        return v2k.get(value);
    }
    //вернуть значение для переданного ключа
    @Override
    public String getValue(Long key) {
        return k2v.get(key);
    }
}
