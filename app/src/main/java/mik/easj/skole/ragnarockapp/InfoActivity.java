package mik.easj.skole.ragnarockapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Om os");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();

            }
        return super.onOptionsItemSelected(item);

    }


    public void BtnSeKort(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/place/Ragnarock+-+Museet+for+pop,+rock+og+ungdomskultur/@55.6279123,12.0806975,16.54z/data=!4m5!3m4!1s0x46525fe994707493:0x4105f77171824821!8m2!3d55.6278195!4d12.0830149"));
        startActivity(intent);
    }

    public void SeEvents(View view) {
        Uri uri = Uri.parse("http://museumragnarock.dk/2017/04/04/events/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void Btn_Youtube(View view) {
        Uri uri = Uri.parse("https://www.youtube.com/user/DanmarksRockmuseum");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void Btn_instagram(View view) {
        Uri uri = Uri.parse("https://www.instagram.com/museumragnarock/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void Btn_Facebook(View view) {
        Uri uri = Uri.parse("https://www.facebook.com/MuseumRagnarock");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
