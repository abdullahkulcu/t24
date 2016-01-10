package com.yoslabs.t24haber;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Duration of wait **/
        final int SPLASH_DISPLAY_LENGTH = 1000;

        /** run for a sec **/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /** intent that will start news class **/

                Intent mainIntent = new Intent(MainActivity.this, NewsActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}
