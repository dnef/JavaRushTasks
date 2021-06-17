package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {
    public void testStorage(Shortener shortener){

        //Создавать три строки. Текст 1 и 3 строк должен быть одинаковым
        String s1 = "172uxobn3rwgzah0v8y5upcmw1";
        String s2 = Helper.generateRandomString();
        String s3 = "172uxobn3rwgzah0v8y5upcmw1";

        //олучать и сохранять идентификаторы для всех трех строк с помощью shortener
        Long id1 = shortener.getId(s1);
        Long id2 = shortener.getId(s2);
        Long id3 = shortener.getId(s3);

        //Проверять, что идентификатор для 2 строки не равен идентификатору для 1 и 3 строк
        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);

        //Проверять, что идентификаторы для 1 и 3 строк равны
        Assert.assertEquals(id1, id3);
        //Получать три строки по трем идентификаторам с помощью shortener
        //Проверять, что строки, полученные в предыдущем пункте, эквивалентны оригинальным
        Assert.assertEquals(s1, shortener.getString(id1));
        Assert.assertEquals(s2, shortener.getString(id2));
        Assert.assertEquals(s3, shortener.getString(id3));
    }
    //добавь в класс FunctionalTest тесты:
    //Каждый тест должен иметь аннотацию @Test, создавать подходящую стратегию,
    // создавать объект класса Shortener на базе этой стратегии и вызывать метод testStorage для него.
    @Test
    public void testJDBCShortener(){
        JdbcStrategy jdbcStrategy = new JdbcStrategy();
        jdbcStrategy.DeleteRowAll();
        Shortener shortener = new Shortener(jdbcStrategy);
        testStorage(shortener);
    }
    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy oursStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(oursStrategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        FileStorageStrategy fileStrategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(fileStrategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy biStrategi = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(biStrategi);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        HashBiMapStorageStrategy guavaMap = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(guavaMap);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy apacheBiDi = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(apacheBiDi);
        testStorage(shortener);
    }
}
