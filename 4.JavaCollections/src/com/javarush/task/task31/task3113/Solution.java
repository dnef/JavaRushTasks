package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/

public class Solution extends SimpleFileVisitor<Path>{
    private int dirs;
    private  int files;
    private long size;
    public int getDirs() {
        return dirs;
    }
    public int getFiles() {
        return files;
    }
    public long getSize() {
        return size;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        dirs++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        files++;
        size=size+Files.size(file);
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        //EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        final Solution solution = new Solution();
        if(Files.isDirectory(Paths.get(path))){
            Files.walkFileTree(Paths.get(path),solution);
            System.out.println("Всего папок - "+(solution.getDirs()-1));
            System.out.println("Всего файлов - "+solution.getFiles());
            System.out.println("Общий размер - "+solution.getSize());
        }else{
            String absolute = Paths.get(path).toAbsolutePath().toString();
            System.out.println(absolute+" - не папка");
        }

    }
}
