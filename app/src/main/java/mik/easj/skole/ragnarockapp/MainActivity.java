package mik.easj.skole.ragnarockapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends AppCompatActivity {

 
    private final String TWIT_CONS_KEY = "3rUaDPL9mO4qzMW335RLfo9dv";
    private final String TWIT_CONS_SEC_KEY = "v81klgIw2vA4n8vgyXt2Wh2SaHEVANx5Ds17XQVamyC7TFxn2r";

    private ListView mainListView;
    private ArrayAdapter<Tweet> adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainListView = (ListView) findViewById(R.id.mainListView);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.toolbarOmOs:
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                return true;
            
            case R.id.toolbarSorterEfterNyeste:
                // code coming
            case R.id.toolbarSorterEfterRetweets:
                // code coming
            case R.id.toolbarSorterEfterLikes:
                // code coming
        }

        return super.onOptionsItemSelected(item);
    }

    //bruger det valgte Twitter # til at søge på
    @Override
    protected void onStart() {
        super.onStart();
        new SearchOnTwitter().execute("#mbmeasjhack");
    }

    public void mainFloatBtnClicked(View view) {
        Intent intent = null;
        try {
            // get the Twitter app if possible
            this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=magnus_em"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/magnus_em"));
        }
        this.startActivity(intent);
    }

    class SearchOnTwitter extends AsyncTask<String, Void, Integer> {
        ArrayList<Tweet> tweets;
        final int SUCCESS = 0;
        final int FAILURE = SUCCESS + 1;

        @Override
        protected Integer doInBackground(String... params) {
            try {
                ConfigurationBuilder builder = new ConfigurationBuilder();
                //builder.setUseSSL(true);
                builder.setApplicationOnlyAuthEnabled(true);
                builder.setOAuthConsumerKey(TWIT_CONS_KEY);
                builder.setOAuthConsumerSecret(TWIT_CONS_SEC_KEY);

                OAuth2Token token = new TwitterFactory(builder.build()).getInstance().getOAuth2Token();

                builder = new ConfigurationBuilder();
                //builder.setUseSSL(true);
                builder.setApplicationOnlyAuthEnabled(true);
                builder.setOAuthConsumerKey(TWIT_CONS_KEY);
                builder.setOAuthConsumerSecret(TWIT_CONS_SEC_KEY);
                builder.setOAuth2TokenType(token.getTokenType());
                builder.setOAuth2AccessToken(token.getAccessToken());

                Twitter twitter = new TwitterFactory(builder.build()).getInstance();

                twitter4j.Query query = new twitter4j.Query(params[0]);
                // YOu can set the count of maximum records here
                query.setCount(50);
                QueryResult result;
                result = twitter.search(query);
                final List<twitter4j.Status> tweets = result.getTweets();
                StringBuilder str = new StringBuilder();
                if (tweets != null) {
                    this.tweets = new ArrayList<Tweet>();
                    for (twitter4j.Status tweet : tweets) {
                        String msgTweet = CheckForRude(tweet.getText());
                        str.append("@" + tweet.getUser().getScreenName() + " - " + msgTweet + "\n");
                        System.out.println(str);
                        int i = (int)tweet.getId();
                        this.tweets.add(new Tweet("@" + tweet.getUser().getScreenName(), tweet.getText(),i ));
                    }
                    return SUCCESS;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return FAILURE;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if (result == SUCCESS) {
                mainListView.setAdapter(new TweetAdapter(MainActivity.this, tweets));
            } else {
                Toast.makeText(MainActivity.this, "Error" + result, Toast.LENGTH_SHORT).show();
            }
        }


    }

    private String CheckForRude(String x) {
        if (x.contains("shit")){
            return "";
        }
        else {return x;}
    }


}
