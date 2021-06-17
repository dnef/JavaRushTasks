package com.javarush.task.task35.task3505;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K,E extends Convertable<K>>Map<K,E> convert(List<E> list) {
        Map<K,E> map = new HashMap<>(list.size());
        for (int i=0;i<list.size();i++){
            map.put(list.get(i).getKey(), list.get(i));
        }

//        Map result = new HashMap();
        return map;
    }
}
