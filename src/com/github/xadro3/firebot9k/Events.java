package com.github.xadro3.firebot9k;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

/**
 * Created by fabio on 03.06.2017.
 */
public class Events {



    @EventSubscriber
    public void messageReceived (MessageReceivedEvent event){
        if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX)){
            BotUtils.sendMessage(event.getChannel(),"Its a test lol");

        }
        if(event.getMessage().getContent().startsWith("§")) {

            EmbedBuilder builder = new EmbedBuilder();

            builder.appendField("fieldTitleInline", "fieldContentInline", true);
            builder.appendField("fieldTitleInline2", "fieldContentInline2", true);
            builder.appendField("fieldTitleNotInline", "fieldContentNotInline", false);
            builder.appendField(":tada: fieldWithCoolThings :tada:", "[hiddenLink](http://i.imgur.com/Y9utuDe.png)", false);

            builder.withAuthorName("authorName");
            builder.withAuthorIcon("http://i.imgur.com/PB0Soqj.png");
            builder.withAuthorUrl("http://i.imgur.com/oPvYFj3.png");

            builder.withColor(255, 0, 0);
            builder.withDesc("withDesc");
            builder.withDescription("withDescription");
            builder.withTitle("withTitle");
            builder.withTimestamp(100);
            builder.withUrl("http://i.imgur.com/IrEVKQq.png");
            builder.withImage("http://i.imgur.com/agsp5Re.png");

            builder.withFooterIcon("http://i.imgur.com/Ch0wy1e.png");
            builder.withFooterText("footerText");
            builder.withFooterIcon("http://i.imgur.com/TELh8OT.png");
            builder.withThumbnail("http://i.imgur.com/7heQOCt.png");

            builder.appendDesc(" + appendDesc");
            builder.appendDescription(" + appendDescription");

            RequestBuffer.request(() -> event.getChannel().sendMessage(builder.build()));



        }

    }


}
