package com.expresstemplate.bmicalc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Splash extends Activity {

    TextView tx_title, tx_subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        tx_title = findViewById(R.id.tx_title);
        tx_subtitle = findViewById(R.id.tx_subtitle);

        Typeface tfbold = Typeface.createFromAsset(Splash.this.getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface tfmedium = Typeface.createFromAsset(Splash.this.getAssets(), "fonts/Raleway-Medium.ttf");

        tx_title.setTypeface(tfbold);
        tx_subtitle.setTypeface(tfmedium);

        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent i = new Intent(getBaseContext(), Home.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {

                }

            }
        };
        th.start();

    }
}
