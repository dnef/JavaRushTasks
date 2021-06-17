package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?> collection[] = Collections.class.getDeclaredClasses();
        Class rez = null;
        for (Class coll : collection) {
            int modifier = coll.getModifiers();

            if (Modifier.isStatic(modifier) && Modifier.isPrivate(modifier)) {
                Method method = null;
                try {
                    method = coll.getDeclaredMethod("get", int.class);
                    method.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    //e.printStackTrace();
                }
                Constructor constructor = null;
                try {
                    constructor = coll.getDeclaredConstructor();
                    constructor.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    //e.printStackTrace();
                }
                try {
                    if (constructor != null) {
                        method.invoke(constructor.newInstance(), 0);
                    }
                } catch (IllegalAccessException | NullPointerException e) {
                    //e.printStackTrace();

                } catch (InvocationTargetException e) {
                    if (e.getCause().toString().contains("IndexOutOfBoundsException")) {
                        rez = coll;
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();

                }
            }
        }
            return rez;
        }
    }
