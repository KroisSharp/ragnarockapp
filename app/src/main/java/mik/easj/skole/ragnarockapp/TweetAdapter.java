package mik.easj.skole.ragnarockapp;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Benjamin-pc on 16-04-2018.
 */


public class TweetAdapter extends BaseAdapter {
    ArrayList<Tweet> tweetList;
    Context context;

    public TweetAdapter(Context context, ArrayList<Tweet> tweetList) {
        this.tweetList = tweetList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tweetList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.tweet_list_item, null);
        }

        Tweet tweet = tweetList.get(position);
        TextView txtTweet = (TextView) convertView.findViewById(R.id.txtTweet);
        TextView txtTweetBy = (TextView) convertView.findViewById(R.id.txtTweetBy);
        ImageView imgview = (ImageView) convertView.findViewById(R.id.image);

        txtTweet.setText(tweet.getTweet());
        txtTweetBy.setText(tweet.getTweetBy());
        new DownloadImageFromInternet(imgview).execute("https://twitter.com/" + tweet.getTweetBy() +"/profile_image?size=bigger");




        return convertView;
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("DownloadImage", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }


}
