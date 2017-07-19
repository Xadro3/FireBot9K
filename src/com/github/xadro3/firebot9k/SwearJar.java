package com.github.xadro3.firebot9k;


import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.tritonus.share.ArraySet;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.io.File;
import java.io.IOException;
import java.util.Set;

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

            db1.put(bytes("1"), bytes("ASS"));
            db1.put(bytes("2"), bytes("ARSE"));
            db1.put(bytes("3"), bytes("ASSHOLE"));
            db1.put(bytes("4"), bytes("BASTARD"));
            db1.put(bytes("5"), bytes("BITCH"));
            db1.put(bytes("6"), bytes("BOLLOCKS"));
            db1.put(bytes("7"), bytes("CRAP"));
            db1.put(bytes("8"), bytes("CHRIST"));
            db1.put(bytes("9"), bytes("CUNT"));
            db1.put(bytes("10"), bytes("DAMN"));
            db1.put(bytes("11"), bytes("FUCK"));
            db1.put(bytes("12"), bytes("GODDAMN"));
            db1.put(bytes("13"), bytes("GODSDAMN"));
            db1.put(bytes("14"), bytes("HELL"));
            db1.put(bytes("15"), bytes("MOTHERFUCKER"));
            db1.put(bytes("16"), bytes("JESUS"));
            db1.put(bytes("17"), bytes("NIGGA"));
            db1.put(bytes("18"), bytes("NIGGER"));
            db1.put(bytes("19"), bytes("SHIT"));
            db1.put(bytes("20"), bytes("SHITASS"));
            db1.put(bytes("20"), bytes("TWAT"));


            String[] toCheck = event.getMessage().getContent().toString().split(" ");

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

                        for (int i = 0; i < toCheck.length; i++) {
                            if (value.equals(toCheck[i].toUpperCase())) {
                               score = score-10;

                                nowords = false;
                            }

                        }
                    }
                } finally {

                    iterator.close();
                }

                if(nowords==true){
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
}
