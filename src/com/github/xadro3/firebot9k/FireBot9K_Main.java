package com.github.xadro3.firebot9k;

import sx.blah.discord.api.IDiscordClient;
import com.github.xadro3.firebot9k.LoggerService;

/**
 * Created by fabio on 03.06.2017.
 */
public class FireBot9K_Main {
    public static void main(String[] args) {

        String token = "yor token here";

        LoggerService.log("success message",LoggerService.SUCCESS);
        LoggerService.log("info message", LoggerService.INFO);
        LoggerService.log("error message",LoggerService.ERROR);

        IDiscordClient client = BotUtils.getBuiltDiscordClient(token);

        client.getDispatcher().registerListener( new Events());

        client.login();

    }

}
