package com.github.xadro3.firebot9k;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.SubredditPaginator;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;


/**
 * Created by fabio on 04.06.2017.
 */
public class RedditCommand   {

    public void reddit (MessageReceivedEvent event) {



            UserAgent myUserAgent = UserAgent.of("desktop", "com.github.xadro3.firebot9k", "v0.1", "Dankestmemebot");

            final RedditClient redditClient = new RedditClient(myUserAgent);

            Credentials credentials = Credentials.script("Dankestmemebot", "wertzu", "zziJzLTdssXr1w", "_So7c8-WSIOLCDosfg2dUSMCytU");

            OAuthData authData = null;

            try {
                authData = redditClient.getOAuthHelper().easyAuth(credentials);
            } catch (OAuthException e) {
                e.printStackTrace();
            }

            SubredditPaginator paginator = new SubredditPaginator(redditClient);

            redditClient.authenticate(authData);

            String[] reddarray;

            reddarray = event.getMessage().getContent().split(" ");

            paginator.setLimit(5);

            paginator.setSubreddit(reddarray[1]);

            if (reddarray[1].equals("all")) {

                Listing<Submission> submissions = paginator.next();

                for (Submission s : submissions) {

                    EmbedBuilder ebuilder = new EmbedBuilder();

                    ebuilder.appendField(s.getTitle(),"",true);
                    ebuilder.appendField("Score",s.getScore().toString(),true);

                    ebuilder.withAuthorName("posted by "+s.getAuthor());





                    RequestBuffer.request(() -> event.getChannel().sendMessage(ebuilder.build()));




                }

            } else {

                Listing<Submission> submissions = paginator.next();

                for (Submission s : submissions) {

                    BotUtils.sendMessage(event.getChannel()," \n" + " \n" + " \n" + " \n" + "**" + s.getTitle() + "**" + "\n" + s.getUrl() + "\n" + "**" + s.getScore() + " Punkte." + "**");

                }
            }

        }
    }


