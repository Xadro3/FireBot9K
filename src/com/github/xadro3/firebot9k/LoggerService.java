package com.github.xadro3.firebot9k;



import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;



 class LoggerService {
    private static void logToFile(String message){

        try {
            Writer writer = new BufferedWriter(new FileWriter("log.txt", true));
            writer.write( message + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            LoggerService.logToConsole(e.getMessage(),LoggerService.ERROR);
        }
    }

    private static void logToConsole(String message, int type){
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_CYAN = "\u001B[36m";

        switch (type) {
            case LoggerService.INFO:
                message = ANSI_CYAN + message + ANSI_RESET;
                break;
            case LoggerService.ERROR:
                message = ANSI_RED  + message + ANSI_RESET;
                break;
            case LoggerService.SUCCESS:
                message = ANSI_GREEN + message + ANSI_RESET;
                break;
            default:
                break;
        }
        System.out.println(message);
    }

   static void log(String message, int type){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ");
        switch (type) {
            case LoggerService.INFO:
                message = "INFO - " + message;
                break;
            case LoggerService.ERROR:
                message = "ERRO - " + message;
                break;
            case LoggerService.SUCCESS:
                message = "SUCC - " + message;
                break;
            default:
                message = "XXXX - " + message;
                break;
        }
        message = sdf.format(new Date()) + message;
        LoggerService.logToFile(message);
        LoggerService.logToConsole(message,type);
    }

    public static void log(String message){
        LoggerService.log(message,LoggerService.INFO);
    }

    static final int INFO = 1;
    static final int ERROR = 2;
    static final int SUCCESS = 3;
}
