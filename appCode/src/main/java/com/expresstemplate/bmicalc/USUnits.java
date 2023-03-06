package com.expresstemplate.bmicalc;

import static com.best.now.myad.utils.PublicHelperKt.showInterstitialAd;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.interstitial.InterstitialAd;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class USUnits extends Activity {

    RelativeLayout relMale, relFemale, relCalculator;
    TextView txtTitle, txtAge, txtHeight, txtWeight, txtGender, txtMale, txtFemale, txtCalcute;
    EditText editFeet, editInches, editWeight, editAge;
    String strFeet, strInch, strWeight, strAge;
    Float floatFeet, floatInch, floatWeight, floatTotalInch, floatMultiplication, floatBmi;
    AlertDialogManager alert = new AlertDialogManager();
    RelativeLayout ad;
    private ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_usunits);

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

        Typeface tf = Typeface.createFromAsset(USUnits.this.getAssets(), "fonts/Raleway-Light.ttf");
        Typeface tf1 = Typeface.createFromAsset(USUnits.this.getAssets(), "fonts/Raleway-Bold.ttf");


        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtAge = (TextView) findViewById(R.id.txtAge);
        txtHeight = (TextView) findViewById(R.id.txtHeight);
        txtWeight = (TextView) findViewById(R.id.txtWeight);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtMale = (TextView) findViewById(R.id.txtMale);
        txtFemale = (TextView) findViewById(R.id.txtFemale);
        txtCalcute = (TextView) findViewById(R.id.txtCalcute);

        txtTitle.setTypeface(tf1);
        txtAge.setTypeface(tf);
        txtHeight.setTypeface(tf);
        txtWeight.setTypeface(tf);
        txtGender.setTypeface(tf);
        txtMale.setTypeface(tf);
        txtFemale.setTypeface(tf);
        txtCalcute.setTypeface(tf1);

        editFeet = (EditText) findViewById(R.id.editFeet);
        editInches = (EditText) findViewById(R.id.editInches);
        editWeight = (EditText) findViewById(R.id.editWeight);
        editAge = (EditText) findViewById(R.id.editAge);

        relCalculator = (RelativeLayout) findViewById(R.id.relCalculator);
        relMale = (RelativeLayout) findViewById(R.id.relMale);
        relFemale = (RelativeLayout) findViewById(R.id.relFemale);

        relMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relMale.setBackgroundResource(R.drawable.selected1);
                relFemale.setBackgroundResource(R.drawable.un_selected2);

            }
        });

        relFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relMale.setBackgroundResource(R.drawable.un_selected);
                relFemale.setBackgroundResource(R.drawable.selected2);

            }
        });

        relCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContinueIntent();

            }
        });
        showInterstitialAd(this, null);
    }

    private void ContinueIntent() {
        // TODO Auto-generated method stub
        strAge = editAge.getText().toString();
        strFeet = editFeet.getText().toString();
        strInch = editInches.getText().toString();
        strWeight = editWeight.getText().toString();

        if (strAge.equals("")) {
            editAge.setError("Enter Age");
        } else if (strInch.equals("")) {
            editInches.setError("Enter Inch");
        } else if (strWeight.equals("")) {
            editWeight.setError("Enter Weight");
        } else if (strFeet.equals("")) {
            editFeet.setError("Enter Feet");
        } else {

            try {

                floatFeet = Float.parseFloat(strFeet);
                floatInch = Float.parseFloat(strInch);
                floatWeight = Float.parseFloat(strWeight);

            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            floatFeet = floatFeet * 12;
            Log.d("floatFeet", "" + floatFeet);
            floatTotalInch = floatFeet + floatInch;
            Log.d("floatFeet1", "" + floatTotalInch);

            floatMultiplication = floatWeight * 703;
            Log.d("floatFeet2", "" + floatMultiplication);

            floatBmi = floatMultiplication / (floatTotalInch * floatTotalInch);
            Log.d("floatFeet3", "" + floatBmi);

            String formattedString = String.format("%.02f", floatBmi);

            String strHeight = Float.toString(floatTotalInch);

            Intent i = new Intent(USUnits.this, DetailPage.class);
            i.putExtra("Answer", formattedString);
            i.putExtra("Height", strHeight + " inch");
            i.putExtra("Weight", strWeight + " lbs");
            i.putExtra("Page", "US");
            startActivity(i);
        }
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
