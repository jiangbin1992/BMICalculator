package com.expresstemplate.bmicalc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;


public class DetailPage extends Activity {

    Button btnBack, btnSave, btnShare;
    float floatAnswer;
    DataManager dataManager;
    String strAnswer, strPage, strHeight, strWeight;
    boolean isSave = false;
    RelativeLayout ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detail_page);

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

        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnShare = (Button) findViewById(R.id.btnShare);

        TextView textView = (TextView) findViewById(R.id.textView);
        TextView txtAnswer = (TextView) findViewById(R.id.txtAnswer);
        TextView txtType = (TextView) findViewById(R.id.txtType);
        TextView txtExtraText = (TextView) findViewById(R.id.txtExtraText);
        TextView txtunderWeight = (TextView) findViewById(R.id.txtunderWeight);
        TextView txtUnderweightAns = (TextView) findViewById(R.id.txtUnderweightAns);
        TextView txtNormal = (TextView) findViewById(R.id.txtNormal);
        TextView txtNormalAns = (TextView) findViewById(R.id.txtNormalAns);
        TextView txtOverWeight = (TextView) findViewById(R.id.txtOverWeight);
        TextView txtOverweightAns = (TextView) findViewById(R.id.txtOverweightAns);
        TextView txtObese = (TextView) findViewById(R.id.txtObese);
        TextView txtObeseAns = (TextView) findViewById(R.id.txtObeseAns);
        TextView txtSeObese = (TextView) findViewById(R.id.txtSeObese);
        TextView txtSeObeseAns = (TextView) findViewById(R.id.txtSeObeseAns);
        TextView txtVsb = (TextView) findViewById(R.id.txtVsb);
        TextView txtVsbAns = (TextView) findViewById(R.id.txtVsbAns);

        RelativeLayout relAnalysis = (RelativeLayout) findViewById(R.id.relAnalysis);

        Typeface tf1 = Typeface.createFromAsset(DetailPage.this.getAssets(), "fonts/Raleway-Medium.ttf");
        Typeface tf = Typeface.createFromAsset(DetailPage.this.getAssets(), "fonts/Raleway-Bold.ttf");

        textView.setTypeface(tf1);
        txtType.setTypeface(tf1);
        txtExtraText.setTypeface(tf1);
        txtunderWeight.setTypeface(tf1);
        txtNormal.setTypeface(tf1);
        txtOverWeight.setTypeface(tf1);
        txtObese.setTypeface(tf1);
        txtSeObese.setTypeface(tf1);
        txtVsb.setTypeface(tf1);

        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setColor(Color.WHITE);
        gd.setStroke(1, Color.WHITE);
        gd.setCornerRadius(10.0f);

        relAnalysis.setBackground(gd);

        txtUnderweightAns.setText("< 18.5");
        txtVsbAns.setText("> 40");

        Intent i = getIntent();
        strAnswer = i.getStringExtra("Answer");
        strPage = i.getStringExtra("Page");
        strHeight = i.getStringExtra("Height");
        strWeight = i.getStringExtra("Weight");

        dataManager = new DataManager(DetailPage.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isSave == false) {
                    dataManager.InsertWord(strAnswer, strHeight, strWeight);
                    isSave = true;

                    AlertDialog alertDialog = new AlertDialog.Builder(DetailPage.this).create();
                    alertDialog.setTitle("Save");
                    alertDialog.setMessage("Your data successfully saved.");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

                    alertDialog.show();

                } else if (isSave == true) {

                    AlertDialog alertDialog = new AlertDialog.Builder(DetailPage.this).create();
                    alertDialog.setTitle("Save");
                    alertDialog.setMessage("You have already saved this data.");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

                    alertDialog.show();

                } else {
                    isSave = false;
                }
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "AndroidSolved");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Your BMI:" + " " + strAnswer);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        txtAnswer.setText("BMI : " + strAnswer);

        try {

            floatAnswer = Float.parseFloat(strAnswer);

        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        if (floatAnswer < 18.5) {

            txtType.setText("Under weight".toUpperCase());
            txtExtraText.setText("Oops! You really need to take care of yours better! Feel to eat more?");

        } else if (floatAnswer >= 18.5 && floatAnswer < 25.0) {

            txtType.setText("Normal".toUpperCase());
            txtExtraText.setText("Congratulations! You are in a good shape!");

        } else if (floatAnswer >= 25.0 && floatAnswer < 30.0) {

            txtType.setText("Over weight".toUpperCase());
            txtExtraText.setText("Oops! You really need to take care of yourself! Workout maybe?");

        } else if (floatAnswer >= 30.0 && floatAnswer < 35.0) {

            txtType.setText("Obese".toUpperCase());
            txtExtraText.setText("Oops! You really need to take care of yourself! Workout maybe?");

        } else if (floatAnswer >= 35.0 && floatAnswer < 40.0) {

            txtType.setText("Severely Obese".toUpperCase());
            txtExtraText.setText("OMG! You are in a very dangerous condition! Act now!");

        } else {

            txtType.setText("Very Severely Obese".toUpperCase());
            txtExtraText.setText("OMG! You are in a very dangerous condition! Act now!");

        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (strPage != null) {
                    if (strPage.equals("US")) {

                        Intent i = new Intent(DetailPage.this, USUnits.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();

                    } else if (strPage.equals("Metric")) {

                        Intent i = new Intent(DetailPage.this, MetricUnits.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();

                    }
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
