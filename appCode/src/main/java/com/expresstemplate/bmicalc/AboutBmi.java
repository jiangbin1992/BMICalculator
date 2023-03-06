package com.expresstemplate.bmicalc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class AboutBmi extends Activity {

    RelativeLayout ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_about_bmi);

        statusBar_Color();

        ad = (RelativeLayout) findViewById(R.id.ad);
//        AdView mAdView = (AdView) findViewById(R.id.adView);

        if (getResources().getString(R.string.ads_visibility).equals("yes")) {
            ad.setVisibility(View.VISIBLE);
//            AdRequest adRequest = new AdRequest.Builder().build();
//            mAdView.loadAd(adRequest);
        } else {
            ad.getLayoutParams().height = 0;
        }

        Typeface tf1 = Typeface.createFromAsset(AboutBmi.this.getAssets(),"fonts/Raleway-Bold.ttf");

        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setTypeface(tf1);


        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/About BMI.html");


    }

    private void statusBar_Color() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.darkgray));
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

}
