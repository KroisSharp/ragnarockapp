package mik.easj.skole.ragnarockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            case R.id.toolbarInformation:
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
}
