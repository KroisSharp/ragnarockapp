package mik.easj.skole.ragnarockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ValgtTweetActivity extends AppCompatActivity {

    private Tweet Tweets;
    private TextView Specific_Tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valgt_tweet);

        Intent intent = getIntent();
        Tweets = (Tweet) intent.getSerializableExtra("Birds");

        Specific_Tweet = findViewById(R.id.Specific_Tweet);
        Specific_Tweet.setText(Tweets.getTweet());


        setTitle("Tweet");

    }
}
