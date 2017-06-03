package com.github.xadro3.firebot9k;

import sx.blah.discord.api.IDiscordClient;

/**
 * Created by fabio on 03.06.2017.
 */
public class FireBot9K_Main {
    public static void main(String[] args) {

         String token = "MzAwNjc2MDU1NTQ1NTQ0NzA5.DBRe_Q.wC8fmszEySTndL5GDiVOOHqH414";

    IDiscordClient client = BotUtils.getBuiltDiscordClient(token);

    client.getDispatcher().registerListener( new Events());

    client.login();

    }

}
