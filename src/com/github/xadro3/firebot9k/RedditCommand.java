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
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;


/**
 * Created by fabio on 04.06.2017.
 */
public class RedditCommand {

    public void reddit(MessageReceivedEvent event) {


        UserAgent myUserAgent = UserAgent.of("platform", "com.github.xadro3.firebot9k", "v0.1", "redditUsername");

        final RedditClient redditClient = new RedditClient(myUserAgent);

        Credentials credentials = Credentials.script("usrname", "password", "clientID", "clientSecret");

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


        paginator.setLimit(3);

        paginator.setSubreddit(reddarray[1]);

        if (reddarray[1].equals("all")) {

            Listing<Submission> submissions = paginator.next();

            for (Submission s : submissions) {

                EmbedBuilder ebuilder = new EmbedBuilder();


                ebuilder.appendField("Score", s.getScore().toString() + " Points", false);

                ebuilder.withFooterText("posted by /u/" + s.getAuthor());

                if (s.getSelftext().length() > 100) {
                    ebuilder.withDescription(s.getSelftext().substring(0, 100) + "...");
                } else if (s.getSelftext().length() > 0) {
                    ebuilder.withDescription(s.getSelftext());
                } else {
                    ebuilder.withImage(s.getThumbnail());
                }


                ebuilder.withTitle(s.getTitle());
                ebuilder.withTimestamp(s.getCreated().getTime());
                ebuilder.withUrl(s.getUrl());


                ebuilder.appendDesc("[" + "/r/" + s.getSubredditName() + "]" + "(" + "https://www.reddit.com/r/" + s.getSubredditName() + ")");


                RequestBuffer.request(() -> event.getChannel().sendMessage(ebuilder.build()));


            }

        } else {

            Listing<Submission> submissions = paginator.next();

            for (Submission s : submissions) {


                EmbedBuilder ebuilder = new EmbedBuilder();


                ebuilder.appendField("Score", s.getScore().toString() + " Points", false);
                ebuilder.withFooterText("posted by /u/" + s.getAuthor());


                if (s.getSelftext().length() > 99) {
                    ebuilder.withDescription(s.getSelftext().substring(0, 99) + "...");
                } else if (s.getSelftext().length() > 0) {
                    ebuilder.withDescription(s.getSelftext());
                } else {
                    ebuilder.withImage(s.getThumbnail());
                }

                ebuilder.withTitle(s.getTitle());
                ebuilder.withTimestamp(s.getCreated().getTime());
                ebuilder.withUrl(s.getUrl());


                RequestBuffer.request(() -> event.getChannel().sendMessage(ebuilder.build()));
            }
        }

    }
}


