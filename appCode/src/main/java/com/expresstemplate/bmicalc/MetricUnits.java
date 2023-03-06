package com.expresstemplate.bmicalc;

import static com.best.now.myad.utils.PublicHelperKt.isRewarded;
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

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class MetricUnits extends Activity {

    RelativeLayout relMale,relFemale,relCalculator;
    TextView txtTitle,txtAge,txtHeight,txtWeight,txtGender,txtMale,txtFemale,txtCalcute,txtAnswer;
    EditText editFeet,editWeight,editAge;
    String strFeet,strWeight,strAge;
    Float floatFeet,floatWeight,floatBmi,floatMeter;
    double meter = 0.01;
    AlertDialogManager alert = new AlertDialogManager();
    RelativeLayout ad;
    private ConnectionDetector cd;
    public static int countBackPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_metric_units);

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

        Typeface tf = Typeface.createFromAsset(MetricUnits.this.getAssets(),"fonts/Raleway-Light.ttf");
        Typeface tf1 = Typeface.createFromAsset(MetricUnits.this.getAssets(),"fonts/Raleway-Bold.ttf");

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtAge = (TextView) findViewById(R.id.txtAge);
        txtHeight = (TextView) findViewById(R.id.txtHeight);
        txtWeight = (TextView) findViewById(R.id.txtWeight);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtMale = (TextView) findViewById(R.id.txtMale);
        txtFemale = (TextView) findViewById(R.id.txtFemale);
        txtCalcute = (TextView) findViewById(R.id.txtCalcute);
        txtAnswer = (TextView) findViewById(R.id.txtAnswer);

        txtTitle.setTypeface(tf1);
        txtAge.setTypeface(tf);
        txtHeight.setTypeface(tf);
        txtWeight.setTypeface(tf);
        txtGender.setTypeface(tf);
        txtMale.setTypeface(tf);
        txtFemale.setTypeface(tf);
        txtCalcute.setTypeface(tf1);

        editFeet = (EditText) findViewById(R.id.editFeet);
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
        strAge = editAge.getText().toString();
        strFeet = editFeet.getText().toString();
        strWeight = editWeight.getText().toString();

        if (strAge.equals("")) {
            editAge.setError("Enter Age");
        }  else if (strWeight.equals("")) {
            editWeight.setError("Enter Weight");
        } else if (strFeet.equals("")) {
            editFeet.setError("Enter Centimeter");
        } else {

            try {

                floatFeet = Float.parseFloat(strFeet);
                floatWeight = Float.parseFloat(strWeight);
                floatMeter = (float) meter;
                Log.d("floatFeetMetric",""+floatMeter);

            } catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            floatBmi = floatWeight/((floatFeet*floatMeter)*(floatFeet*floatMeter));
            Log.d("floatFeetMetric2",""+floatBmi);

            String formattedString = String.format("%.02f", floatBmi);
            Log.d("floatFeetMetric3",""+formattedString);

            Intent i = new Intent(MetricUnits.this,DetailPage.class);
            i.putExtra("Answer",formattedString);
            i.putExtra("Height",strFeet+" centimeters");
            i.putExtra("Weight",strWeight+" kgs");
            i.putExtra("Page","Metric");
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
