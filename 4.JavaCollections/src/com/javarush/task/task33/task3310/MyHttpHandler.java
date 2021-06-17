package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.JdbcStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String paramURI = null;
        if ("GET".equals(httpExchange.getRequestMethod())){
            paramURI=handleGetRequest(httpExchange);
        } else if("POST".equals(httpExchange)) {
            paramURI = handlePostRequest(httpExchange);
    }
        handleResponse(httpExchange,paramURI);

}

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }
    private String handlePostRequest(HttpExchange httpExchange) {
        return null;
    }
    private void handleResponse(HttpExchange httpExchange, String paramURI) throws IOException {
        String str = "null";
        OutputStream outputStream = httpExchange.getResponseBody();
        JdbcStrategy jdbcStrategy = new JdbcStrategy();
        StorageStrategy storageStrategy = jdbcStrategy;
        Shortener shortener = new Shortener(storageStrategy);
        if(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[0].equals("string")){
            Long id = Long.valueOf(paramURI);
            str = shortener.getString(id);
        }else{if(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[0].equals("id")){
            str = String.valueOf(shortener.getId(paramURI));
        }}
        httpExchange.sendResponseHeaders(200,str.length());
        outputStream.write(str.getBytes());
        outputStream.flush();
        outputStream.close();
//        jdbcStrategy.DeleteRowAll();
//        storageStrategy.put(Long.valueOf("qwerty".hashCode()),"qwerty");
//        storageStrategy.put(Long.valueOf("asdfgh".hashCode()),"asdfgh");
//        storageStrategy.put(Long.valueOf("zxcvbn".hashCode()),"zxcvbn");
        //Long id = shortener.getId(paramURI);

    }


    //просто проверка
//    public static void main(String[] args) {
//        StorageStrategy storageStrategy = new JdbcStrategy();
//        Shortener shortener = new Shortener(storageStrategy);
//        storageStrategy.put(1L,"qwerty");
//        storageStrategy.put(2L,"asdfgh");
//        storageStrategy.put(3L,"zxcvbn");
//
//        Long id = shortener.getId("asdfgh");
//        String str = shortener.getString(id);
//        System.out.println("id - "+id+" val-"+str);
//
//    }
}
