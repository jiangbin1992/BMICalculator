package com.expresstemplate.bmicalc;

import static com.best.now.myad.utils.PublicHelperKt.isRewarded;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Home extends Activity {

    RelativeLayout relUsUnit, relMetricUnit, relAnalysis, relAboutUs;
    AlertDialogManager alert = new AlertDialogManager();
    RelativeLayout ad;
    private ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        statusBar_Color();

        ad = (RelativeLayout) findViewById(R.id.ad);
        AdView mAdView = (AdView) findViewById(R.id.adView);

        if (getResources().getString(R.string.ads_visibility).equals("yes")) {
            ad.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        } else {
            ad.getLayoutParams().height = 0;
        }

        Typeface tf = Typeface.createFromAsset(Home.this.getAssets(), "fonts/Raleway-Bold.ttf");
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setTypeface(tf);

        Button btnShare = (Button) findViewById(R.id.btnShare);
        Button btnRate = (Button) findViewById(R.id.btnRate);
        Button btnMore = (Button) findViewById(R.id.btnMore);

        relUsUnit = (RelativeLayout) findViewById(R.id.relUsUnit);
        relMetricUnit = (RelativeLayout) findViewById(R.id.relMetricUnit);
        relAnalysis = (RelativeLayout) findViewById(R.id.relAnalysis);
        relAboutUs = (RelativeLayout) findViewById(R.id.relAboutUs);

        relUsUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRewarded(Home.this)){
                    Intent i = new Intent(Home.this, USUnits.class);
                    startActivity(i);
                }
            }
        });

        relMetricUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRewarded(Home.this)) {
                    Intent i = new Intent(Home.this, MetricUnits.class);
                    startActivity(i);
                }
            }
        });

        relAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRewarded(Home.this)) {
                    Intent i = new Intent(Home.this, History.class);
                    startActivity(i);
                }
            }
        });

        relAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, AboutBmi.class);
                startActivity(i);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Xyz");
                share.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharing_text) + "\n" + "https://play.google.com/store/apps/details?id=" + Home.this.getPackageName());
                startActivity(Intent.createChooser(share, "Share Link!"));
            }
        });

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + Home.this.getPackageName());
                Intent iv = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(iv);
            }
        });

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri1 = Uri.parse("https://play.google.com/store/apps/developer?id=" + getString(R.string.more_apps) + Home.this.getPackageName());
                Intent iv1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(iv1);
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog alertDialog = new AlertDialog.Builder(Home.this).create();
        alertDialog.setTitle("Closing Application");
        alertDialog.setMessage("Are you sure you want to close this application?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                Home.this.finishAffinity();
            }

        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialog.show();

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
