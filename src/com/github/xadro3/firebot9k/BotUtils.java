package com.github.xadro3.firebot9k;


import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

/**
 * Created by fabio on 03.06.2017.
 */
 class BotUtils {

    static String BOT_PREFIX = "!";



    static IDiscordClient getBuiltDiscordClient(String token){
        return new ClientBuilder().withToken(token).build();
    }




static void sendMessage (final IChannel channel, final String message) {

    RequestBuffer.request(() -> {
        try {
            channel.sendMessage(message);
        } catch (DiscordException e) {
            LoggerService.log("Message could not be sent with error: ",LoggerService.ERROR);
            e.printStackTrace();
        }
    });

}
}
