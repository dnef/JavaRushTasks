package com.javarush.task.task31.task3112;

import com.sun.jndi.toolkit.url.Uri;
import com.sun.jndi.toolkit.url.UrlUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();
        String name = Paths.get(url.getPath()).getFileName().toString();
        Path temp = Files.createTempFile("javarush",".tmp");
        Files.copy(inputStream,temp);
        Files.move(temp,Paths.get(downloadDirectory+"/"+name),StandardCopyOption.REPLACE_EXISTING);
        //Files.copy(temp,Paths.get(downloadDirectory+"/"+name),StandardCopyOption.REPLACE_EXISTING);
        return Paths.get(downloadDirectory + "/" + name);
    }
}
