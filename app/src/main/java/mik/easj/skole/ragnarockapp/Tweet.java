package mik.easj.skole.ragnarockapp;

import java.io.Serializable;

/**
 * Created by Benjamin-pc on 16-04-2018.
 */

public class Tweet implements Serializable {
    String tweetBy;
    String tweet;
    private int id;

    public Tweet(String tweetBy, String tweet,int id) {
        this.tweetBy = tweetBy;
        this.tweet = tweet;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTweetBy() {
        return tweetBy;
    }

    public void setTweetBy(String tweetBy) {
        this.tweetBy = tweetBy;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

}
