package com.github.xadro3.firebot9k;


import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

/**
 * since configuration should be consistent during runtime
 * the file will be loaded once and the class is a singleton
 */
public class Configuration {
    private static Configuration instance;
    public static Configuration getInstance()
    {
        if (instance == null)
        {
            instance = new Configuration();
        }

        return instance;
    }
    //here are variables that are loaded from the ini file
    private String token;

    public String getToken() {
        return token;
    }

    protected Configuration()
    {
        LoggerService.log("Reading the data from the settings.ini file.");
        Wini iniFile;
        try {
            iniFile = new Wini(new File("./settings.ini"));
        } catch (IOException e) {
            LoggerService.log(e.getMessage(), LoggerService.ERROR);
            return;
        }
        try {
            this.token = iniFile.get("connection","token",String.class);
        } catch (Exception e) {
            LoggerService.log(e.getMessage(),LoggerService.ERROR);
            return;
        }
        LoggerService.log("Data from settings.ini is loaded.",LoggerService.SUCCESS);
    }

    // Any other methods
}
