package com.github.xadro3.firebot9k;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;




public class FireBot9K_Main {
    public static void main(String[] args) {





         String token = "MzAwNjc2MDU1NTQ1NTQ0NzA5.C9lhSg.OaB0XdcZmEIcIGe1rH8wJVuQXB0";      //This is still harcoded since  we are still working on a .ini implementation for our settings



        LoggerService.log("success message",LoggerService.SUCCESS);
        LoggerService.log("info message", LoggerService.INFO);
        LoggerService.log("error message",LoggerService.ERROR);

        IDiscordClient client = BotUtils.getBuiltDiscordClient(token);



        client.getDispatcher().registerListener(new Events());



        try {
            client.login();
        } catch (DiscordException e){
            System.err.print("Login error: ");
            e.printStackTrace();
        }

    }

}
