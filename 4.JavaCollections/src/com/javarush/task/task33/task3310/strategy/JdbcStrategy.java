package com.javarush.task.task33.task3310.strategy;

import java.sql.*;
import java.util.Collection;

public class JdbcStrategy implements StorageStrategy {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/testStrategy";
    static final String USER = "postgres";
    static final String PASS = "dfhgbujh";
    static Connection connection = null;
    static Statement statement = null;

    public void BaseConections() {
        try {
            this.connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean containsKey(Long key) {
        ResultSet resultSet=null;
        int size=0;
        try {
            BaseConections();
            statement = connection.createStatement();
           resultSet = statement.executeQuery("SELECT * FROM strings where id="+key );
            size = resultSet.getFetchSize();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return size!=0;
    }

    @Override
    public boolean containsValue(String value) {
        ResultSet resultSet=null;
        int size=0;
        try {
            BaseConections();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM strings where string="+"'"+value+"'" );
            size = resultSet.getFetchSize();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return size!=0;
    }

    @Override
    public void put(Long key, String value) {
        try {
            BaseConections();
            key= Long.valueOf(value.hashCode());
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO strings VALUES ("+key+",'"+value+"')");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Long getKey(String value) {
        ResultSet resultSet=null;
        Long id;
        try {
            BaseConections();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT string FROM strings where string='"+value+"'" );
            resultSet.next();
            id= resultSet.getLong("string");
            statement.close();
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        ResultSet resultSet=null;
        String val;
        try {
            BaseConections();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT string FROM strings where id="+key);
            resultSet.next();
            val=resultSet.getString("string");
            statement.close();
            return val;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void DeleteRowAll(){
        try {
            BaseConections();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM strings");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
