package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        return values().size();
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        V oldVal = null;
        List<V> vals = map.get(key);
        if (vals==null){
            vals = new ArrayList<>();
        }else{
            oldVal = vals.get(vals.size()-1);
            if (vals.size()==repeatCount){
                vals.remove(0);
            }
        }
        vals.add(value);
        map.put(key,vals);
        return oldVal;
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        List<V> vals = map.get(key);
        if (vals == null){ return null;}
        V val = vals.get(0);
        vals.remove(0);
        if (vals.size()==0){
            map.remove(key);
        }
        return val;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        List<V> list = new ArrayList<>();
        for(List<V> val:map.values()){
            list.addAll(val);
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return keySet().contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}