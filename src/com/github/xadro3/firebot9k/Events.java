package com.github.xadro3.firebot9k;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Created by fabio on 03.06.2017.
 */
public class Events {

    @EventSubscriber

    public void messageReceived (MessageReceivedEvent event){
        if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX)){
            BotUtils.sendMessage(event.getChannel(),"Its a test lol");
        }
    }


}
