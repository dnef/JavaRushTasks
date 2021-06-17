package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();
        File dir = new File(pathToAnimals);
        File[] files = dir.listFiles();
        for (File file:files){
           if (file.getName().endsWith(".class")){
               Class<?> customLoadClass = null;
               try {
                   customLoadClass = new CustomLoadClass().findClass(file.getAbsolutePath());
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }
               Constructor constructor = null; // получить публичный конструктор без параметров.
               try {
                   try {
                       constructor = customLoadClass.getConstructor();
                   }catch (NoSuchMethodException e){continue;}
                   int modif = constructor.getModifiers();
                   if (Modifier.isPublic(modif) && constructor.getParameterCount() == 0) {
                       if (Animal.class.isAssignableFrom(customLoadClass) ) { //Далее проверяем, что класс наследует Animal.
                           // если вернет true, значит наследует.
                           set.add((Animal) constructor.newInstance());
                       }
                   }
               } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                   e.printStackTrace();
               }


           }
        }
        return set;
    }
}
class CustomLoadClass extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        return defineClass(null, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String name) {
        File file = new File(name);
        InputStream inputStream = null;
        ByteArrayOutputStream byteStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            byteStream = new ByteArrayOutputStream();
            int nextValue = 0;
            while ((nextValue = inputStream.read(buffer)) != -1) {
                byteStream.write(buffer, 0, nextValue);
                return byteStream.toByteArray();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (byteStream!=null){
                    try {
                        byteStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
