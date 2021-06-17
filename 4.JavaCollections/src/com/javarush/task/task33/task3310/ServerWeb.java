package com.javarush.task.task33.task3310;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerWeb {
    public static void main(String[] args) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8001), 0);
            httpServer.createContext("/testJDBC", new MyHttpHandler());
            //ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
            httpServer.setExecutor(null);
            httpServer.start();
            System.out.println("Server start");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
