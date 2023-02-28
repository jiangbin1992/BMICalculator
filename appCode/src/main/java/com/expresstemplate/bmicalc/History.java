package com.expresstemplate.bmicalc;

import static com.best.now.myad.utils.PublicHelperKt.showInterstitialAd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class History extends Activity {

    ListView listViewData;
    List<Word> item_data;
    DataManager dataManager;
    RelativeLayout relList, relText;
    RelativeLayout ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_history);

        statusBar_Color();

        Typeface tf1 = Typeface.createFromAsset(History.this.getAssets(), "fonts/Raleway-Bold.ttf");

        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setTypeface(tf1);

        ad = (RelativeLayout) findViewById(R.id.ad1);
        AdView mAdView = (AdView) findViewById(R.id.adView1);

        if (getResources().getString(R.string.ads_visibility).equals("yes")) {
            ad.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        } else {
            ad.getLayoutParams().height = 0;
        }

        dataManager = new DataManager(History.this);
        listViewData = (ListView) findViewById(R.id.listView);
        Button btnClear = (Button) findViewById(R.id.btnClear);

        relList = (RelativeLayout) findViewById(R.id.relList);
        relText = (RelativeLayout) findViewById(R.id.relText);


        item_data = new ArrayList<Word>();
        item_data = dataManager.getAllHistoryData();

        if (item_data.size() == 0) {
            relText.setVisibility(View.VISIBLE);
            relList.setVisibility(View.GONE);
        } else {
            relText.setVisibility(View.GONE);
            relList.setVisibility(View.VISIBLE);
            this.listViewData.setAdapter(new ListBaseAdapter(History.this.getApplicationContext(), this.item_data));
        }



        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog alertDialog = new AlertDialog.Builder(History.this).create();
                alertDialog.setTitle("History");
                alertDialog.setMessage("Do you want to clear the history ?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        item_data = dataManager.getDeleteHistoryData();
                        listViewData.setAdapter(new ListBaseAdapter(History.this.getApplicationContext(), item_data));
                        relText.setVisibility(View.VISIBLE);
                        relList.setVisibility(View.GONE);
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
        showInterstitialAd(this, null);
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
