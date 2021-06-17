package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    public static Throwable createException(Enum enumException){
        if(enumException==null){
            return new IllegalArgumentException();
        }
        String message = enumException.name().toLowerCase().replaceAll("[_]"," ");
        String firstChar = message.substring(0,1).toUpperCase();
        String returnMessage = firstChar+message.substring(1);

        switch (enumException.getClass().getSimpleName()){
            case ("ApplicationExceptionMessage"):return new Exception(returnMessage);
            case ("DatabaseExceptionMessage") : return new RuntimeException(returnMessage);
            case ("UserExceptionMessage") : return new Error(returnMessage);
            default:return new IllegalArgumentException();
        }
    }
}
