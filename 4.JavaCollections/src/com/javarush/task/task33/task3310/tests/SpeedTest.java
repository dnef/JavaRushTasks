package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    int testSize = 10000;
    //должен возвращать время в миллисекундах необходимое для получения идентификаторов для всех строк из strings.
    // Идентификаторы должны быть записаны в ids.
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date startDate = new Date();
        for (String str:strings){
           ids.add(shortener.getId(str));
        }
        Date stopDate = new Date();
        Long time=stopDate.getTime() - startDate.getTime();
        return time;
    }
    //Он должен возвращать время в миллисекундах необходимое для получения строк для всех идентификаторов из ids.
    // Строки должны быть записаны в strings.
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date startDate = new Date();
        for (Long id:ids){
            if (id!=null) {
                strings.add(shortener.getString(id));
            }
        }
        Date stopDate = new Date();
        Long time=stopDate.getTime() - startDate.getTime();
        return time;
    }
//Создавать два объекта типа Shortener, один на базе HashMapStorageStrategy, второй на базе HashBiMapStorageStrategy.
// Назовем их shortener1 и shortener2.
// Генерировать с помощью Helper 10000 строк и помещать их в сет со строками, назовем его origStrings.
// Получать время получения идентификаторов для origStrings (вызывать метод getTimeToGetIds для shortener1, а затем для shortener2).
// Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 больше, чем для shortener2.
// Получать время получения строк (вызывать метод getTimeToGetStrings для shortener1 и shortener2).
// Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 примерно равно времени для shortener2.
// Используй метод assertEquals(float expected, float actual, float delta). В качестве delta можно использовать 30,
// этого вполне достаточно для наших экспериментов.
    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origString = new HashSet<>();
        for (int i=0 ;i<testSize;i++){
            origString.add(Helper.generateRandomString());
        }
        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        Long time1Shortener1 = getTimeToGetIds(shortener1,origString,ids1);
        Long time1Shortener2 = getTimeToGetIds(shortener2,origString,ids2);
        Assert.assertTrue(time1Shortener1>time1Shortener2);
        Long time2Shortener1 = getTimeToGetStrings(shortener1,ids1,new HashSet<String>());
        Long time2Shortener2 = getTimeToGetStrings(shortener2,ids2,new HashSet<String>());
        Assert.assertEquals((float)time2Shortener1,(float)time2Shortener2,30);
    }
}
