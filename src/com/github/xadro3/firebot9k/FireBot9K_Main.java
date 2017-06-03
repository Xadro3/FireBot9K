package com.github.xadro3.firebot9k;

import sx.blah.discord.api.IDiscordClient;

/**
 * Created by fabio on 03.06.2017.
 */
public class FireBot9K_Main {
    public static void main(String[] args) {

         String token = "yor token here";

    IDiscordClient client = BotUtils.getBuiltDiscordClient(token);

    client.getDispatcher().registerListener( new Events());

    client.login();

    }

}
