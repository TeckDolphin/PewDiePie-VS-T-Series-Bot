import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.*;
import com.google.common.math.BigIntegerMath;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.*;

public class DataReading {
    private final String API_KEY = "AIzaSyBFKZpTslV80WNbK8a6Kii-GXbe8W9oFzg";
    private int pewCurrentSubCount;
    private int tSeriesSubCount;
    private int subGapResult;

    public void pewSubscriberCountUpdate() {
        YouTube youtube = new YouTube.Builder(
                new NetHttpTransport(),
                new JacksonFactory(),
                new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) throws IOException {
                    }
                })
                .setApplicationName("youtube-cmdline-search-sample")
                .setYouTubeRequestInitializer(new YouTubeRequestInitializer(API_KEY))
                .build();


        String queryTerm = "PewDiePie";

        try {
            YouTube.Search.List search = youtube.search().list("snippet");
            search.setQ(queryTerm);
            search.setType("channel");

            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();

            if (searchResultList != null) {

                for (SearchResult searchResult : searchResultList) {
                    String channelId = searchResult.getSnippet().getChannelId();

                    YouTube.Channels.List channels = youtube.channels().list("snippet, statistics");
                    channels.setId(channelId);

                    ChannelListResponse channelResponse = channels.execute();
                    for (Channel c : channelResponse.getItems()) {
                        System.out.println("Subs: " + c.getStatistics().getSubscriberCount());
                        pewCurrentSubCount = c.getStatistics().getSubscriberCount().intValue();
                        break;
                    }
                    break;
                    //I'm trying to figure out how to turn this into not a for loop.
                }
            }
        } catch (Exception e) {
            System.out.println("A problem has been found");
            System.out.println("that I couldn't find and i'm to lazy to find lol");
        }
        // I don't know how to convert this code out of the for loop!
    }

    public void tSeriesUpdate() {
        YouTube youtube = new YouTube.Builder(
                new NetHttpTransport(),
                new JacksonFactory(),
                new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) throws IOException {
                    }
                })
                .setApplicationName("youtube-cmdline-search-sample")
                .setYouTubeRequestInitializer(new YouTubeRequestInitializer(API_KEY))
                .build();


        String queryTerm = "T-Series";

        try {
            YouTube.Search.List search = youtube.search().list("snippet");
            search.setQ(queryTerm);
            search.setType("channel");

            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();

            if (searchResultList != null) {

                for (SearchResult searchResult : searchResultList) {
                    String channelId = searchResult.getSnippet().getChannelId();

                    YouTube.Channels.List channels = youtube.channels().list("snippet, statistics");
                    channels.setId(channelId);

                    ChannelListResponse channelResponse = channels.execute();
                    for (Channel c : channelResponse.getItems()) {
                        System.out.println("Subs: " + c.getStatistics().getSubscriberCount());
                        tSeriesSubCount = c.getStatistics().getSubscriberCount().intValue();
                        break;
                    }
                    break;
                    //I'm trying to figure out how to turn this into not a for loop.
                }
            }
        } catch (Exception e) {
            System.out.println("A problem has been found");
            System.out.println("that I couldn't find and i'm to lazy to find lol");
        }
        // I litteraly Couldn't find anything to help me with getting the subscriber
        // Count it took me 2 hours just to find this give me a break.
    }

    public void calcSubGap() {
        int pewSub = pewCurrentSubCount;
        int tSeriesSub = tSeriesSubCount;
        setSubGapResult(pewSub - tSeriesSub);
    }

    public void getSubGapString(MessageReceivedEvent event) {
        if (getSubGapResult() > 0) {
            event.getChannel().sendMessage
                    ("Pewdiepie is currently " + NumberFormat.getNumberInstance(Locale.US).format(getSubGapResult()) + " subscribers above T-Series!").queue();
            event.getChannel().sendMessage(
                    "https://www.youtube.com/watch?v=UVxU2HzPGug").queue();

        } else if (getSubGapResult() < 0) {
            event.getChannel().sendMessage(
                    "Pewdiepie is currently " + Math.abs(getSubGapResult()) + " subscribers away from T-Series God Help Us.").queue();
            event.getChannel().sendMessage(
                    "https://www.youtube.com/watch?v=UVxU2HzPGug").queue();
            //Add a fail we lost gamers!
            //I can't really test this..... Welp if the day comes then rip.

        }
    }


        public String getTSeriesSubCount () {
            System.out.println(tSeriesSubCount);
            return NumberFormat.getNumberInstance(Locale.US).format(tSeriesSubCount);
        }

        public String getPewCurrentSubCount () {
            return NumberFormat.getNumberInstance(Locale.US).format(pewCurrentSubCount);
        }

        public int getSubGapResult () {
            return subGapResult;
        }

        public void setSubGapResult ( int subGapResult){
            this.subGapResult = subGapResult;
        }



}
