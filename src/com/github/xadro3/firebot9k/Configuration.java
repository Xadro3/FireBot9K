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
    private String usrname;
    private String password;
    private String clientID;
    private String cSecret;
    private String prefix;
    private String flickrAPIKey;
    private String flickrAPISecret;


    public String getPrefix() {
        return prefix;
    }

    public String getToken() {
        return token;
    }

    public String getUsrname() {
        return usrname;
    }

    public String getPassword() {
        return password;
    }

    public String getClientID() {
        return clientID;
    }

    public String getcSecret() {
        return cSecret;
    }

    public String getFlickrAPIKey() {
        return flickrAPIKey;
    }

    public String getFlickrAPISecret() {
        return flickrAPISecret;
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
            this.usrname = iniFile.get("RedditCredentials", "usrname", String.class);
            this.password = iniFile.get("RedditCredentials", "password", String.class);
            this.clientID = iniFile.get("RedditCredentials", "clientID", String.class);
            this.cSecret = iniFile.get("RedditCredentials", "cSecret", String.class);
            this.prefix = iniFile.get("BotPrefix","prefix",String.class);
            this.flickrAPIKey= iniFile.get("Flickr/Imgur Auth keys", "flickrAPIKey",String.class);
            this.flickrAPISecret= iniFile.get("Flickr/Imgur Auth keys", "flickrAPISecret",String.class);
        } catch (Exception e) {
            LoggerService.log(e.getMessage(),LoggerService.ERROR);
            return;
        }
        LoggerService.log("Data from settings.ini is loaded.",LoggerService.SUCCESS);
    }

   /** protected void Permissions()
    {
        LoggerService.log("Reading the data from the permissions.ini file.");
        Wini iniFile;
        try {
            iniFile = new Wini(new File("./permissions.ini"));
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

**/

    // Any other methods
}
