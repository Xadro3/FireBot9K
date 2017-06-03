package com.github.xadro3.firebot9k;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 * Created by fabio on 03.06.2017.
 */
public class FireBot9K_Main {
    public static void main(String[] args) {



         String token = "Your Token here";      //This is still harcoded since  we are still working on a .ini implementation for our settings

    IDiscordClient client = BotUtils.getBuiltDiscordClient(token);

    client.getDispatcher().registerListener( new Events());

    try {
        client.login();
    } catch (DiscordException e){
        System.err.print("Login error: ");
        e.printStackTrace();
    }

    }

}
