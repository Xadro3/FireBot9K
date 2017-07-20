package com.github.xadro3.firebot9k;


import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import static org.fusesource.leveldbjni.JniDBFactory.*;


/**
 * Created by fabio on 19.07.2017.
 */
public class SwearJar {


    public void isItASwearWord(MessageReceivedEvent event) throws IOException {

        Options options1 = new Options();
        options1.createIfMissing(true);
        DB db1 = factory.open(new File("swearwords.db"), options1);
        try {


            String toCheck = event.getMessage().getContent().toString();

            String swearerName = event.getAuthor().getStringID();

            Options options = new Options();
            options.createIfMissing(true);
            DB db = factory.open(new File("swearer.db"), options);
            try {

                if (asString(db.get(bytes(swearerName))) == null) {
                    db.put(bytes(swearerName), bytes("10"));
                }


                int score = Integer.parseInt(asString(db.get(bytes(swearerName))));

                DBIterator iterator = db1.iterator();

                boolean nowords = true;

                try {
                    for (iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {

                        String key = asString(iterator.peekNext().getKey());
                        String value = asString(iterator.peekNext().getValue());


                        if (toCheck.toUpperCase().contains(value)) {
                            score = score - 10;

                            nowords = false;
                        }

                    }

                } finally {

                    iterator.close();
                }

                if (nowords == true) {
                    score++;
                }

                db.put(bytes(swearerName), bytes(((String.valueOf(score)))));

                /**System.out.print(asString(bytes(swearerName)));
                 System.out.print("     " + asString(db.get(bytes(swearerName))) + '\n');
                 System.out.println(nowords);**/

            } finally {

                db.close();
            }


        } finally {
            db1.close();
        }


    }

    public void checkStats(MessageReceivedEvent event) throws IOException {


        Options options = new Options();
        options.createIfMissing(true);
        DB db = factory.open(new File("swearer.db"), options);

        Hashtable<String, Integer> hashtable = new Hashtable<>();

        try {

            DBIterator iterator = db.iterator();


            try {
                for (iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {

                    String key = asString(iterator.peekNext().getKey());
                    String value = asString(iterator.peekNext().getValue());

                    hashtable.put(key, Integer.parseInt(value));

                }
            } finally {

                iterator.close();
            }

        } finally {
            db.close();
        }


        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.withColor(255, 127, 71);

        embedBuilder.withTitle("Your Karma");
        embedBuilder.appendField(event.getAuthor().getName(),
                String.valueOf(hashtable.get(event.getAuthor().getStringID())), true);


        RequestBuffer.request(() -> event.getChannel().sendMessage(embedBuilder.build()));
    }

    public void addWord(MessageReceivedEvent event) throws IOException {


        String[] toAdd = event.getMessage().getContent().split(": ");

        int i = 0;


        Options options1 = new Options();
        options1.createIfMissing(true);
        DB db1 = factory.open(new File("swearwords.db"), options1);
        try {

            DBIterator iterator = db1.iterator();


            try {
                for (iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {
                    String key = asString(iterator.peekNext().getKey());
                    String value = asString(iterator.peekNext().getValue());


                    i++;
                }
            } finally {

                iterator.close();
            }
            i++;

            try {
                db1.put(bytes(String.valueOf(i)), bytes(toAdd[1].toUpperCase()));

                BotUtils.sendMessage(event.getChannel(), "`Added " + toAdd[1] + " to swearwords.db under the Key: " + i + "`");

            } catch (ArrayIndexOutOfBoundsException e) {
                BotUtils.sendMessage(event.getChannel(), "USAGE: `!jar add: *swear you want to add*`");
            }


        } finally {
            db1.close();

        }


    }

}
