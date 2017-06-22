package com.github.xadro3.firebot9k;


import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;




class BotUtils {

     static Configuration cfg = Configuration.getInstance();

    static String BOT_PREFIX = cfg.getPrefix();



    static IDiscordClient getBuiltDiscordClient(String token){

        return new ClientBuilder().withToken(token).withRecommendedShardCount(true).build();


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

   /**  static void sendImage (final IChannel channel, final String string) {



         RequestBuffer.request(() -> {
             try {
                 channel.sendFile();
             } catch (DiscordException e) {
                 LoggerService.log("Message could not be sent with error: ",LoggerService.ERROR);
                 e.printStackTrace();
             }
         });

     }**/
}
