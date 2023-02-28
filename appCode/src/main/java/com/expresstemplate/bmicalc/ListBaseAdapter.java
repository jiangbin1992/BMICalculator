package com.expresstemplate.bmicalc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ListBaseAdapter extends BaseAdapter {

	  private List<Word> itemDetailsrrayList;
	  private LayoutInflater inflater = null;
	  Context contex;
	  
	  public ListBaseAdapter(Context context, List<Word> str) {
	   contex = context;
	   itemDetailsrrayList = str;
	   inflater = (LayoutInflater) contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }

	  @Override
	  public int getCount() {
	   return itemDetailsrrayList.size();
	  }

	  @Override
	  public Object getItem(int position) {
	   return position;
	  }

	  @Override
	  public long getItemId(int position) {
	   return position;
	  }


	@SuppressLint({ "InflateParams", "NewApi" })
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	   View vi = convertView;
		Float floatBmi = null;
		GradientDrawable gd,gd1;
	   if (convertView == null) {

	    vi = inflater.inflate(R.layout.historycell, null);
	    
	   }
	   
		TextView txtBmi = (TextView) vi.findViewById(R.id.txtBmi);
		TextView txtWeighAns = (TextView) vi.findViewById(R.id.txtWeighAns);
		TextView txtHeightAns = (TextView) vi.findViewById(R.id.txtHeightAns);
		TextView txtWeight = (TextView) vi.findViewById(R.id.txtWeight);
		TextView txtHeight = (TextView) vi.findViewById(R.id.txtHeight);

		RelativeLayout relCircleColor = (RelativeLayout) vi.findViewById(R.id.relCircleColor);
		RelativeLayout relCircleColorDark = (RelativeLayout) vi.findViewById(R.id.relCircleColorDark);

		txtBmi.setText(((Word) itemDetailsrrayList.get(position)).getBmi());
		txtWeighAns.setText(((Word) itemDetailsrrayList.get(position)).getWeight());
		txtHeightAns.setText(((Word) itemDetailsrrayList.get(position)).getHeight());

		Typeface tf = Typeface.createFromAsset(contex.getAssets(),"fonts/Raleway-Light.ttf");
		txtWeight.setTypeface(tf);
		txtHeight.setTypeface(tf);

		String bmi = ((Word) itemDetailsrrayList.get(position)).getBmi();

		try {

			 floatBmi = Float.parseFloat(bmi);

		} catch(NumberFormatException nfe) {
			System.out.println("Could not parse " + nfe);
		}

		if (floatBmi<18.5){

			gd1 = new GradientDrawable();
			gd1.setShape(GradientDrawable.RECTANGLE);
			gd1.setCornerRadius(1.0f);
			gd1.setColor(Color.parseColor("#4A3EFF"));
			gd1.setStroke(1, Color.parseColor("#4A3EFF"));
			relCircleColorDark.setBackground(gd1);

			gd = new GradientDrawable();
			gd.setShape(GradientDrawable.RECTANGLE);
			gd.setCornerRadius(1.0f);
			gd.setColor(Color.parseColor("#372ebf"));
			gd.setStroke(1, Color.parseColor("#372ebf"));
			relCircleColor.setBackground(gd);

		} else if (floatBmi>=18.5 && floatBmi<25.0){

			gd1 = new GradientDrawable();
			gd1.setShape(GradientDrawable.RECTANGLE);
			gd1.setCornerRadius(1.0f);
			gd1.setColor(Color.parseColor("#5BCB34"));
			gd1.setStroke(1, Color.parseColor("#5BCB34"));
			relCircleColorDark.setBackground(gd1);

			gd = new GradientDrawable();
			gd.setShape(GradientDrawable.RECTANGLE);
			gd.setCornerRadius(1.0f);
			gd.setColor(Color.parseColor("#449827"));
			gd.setStroke(1, Color.parseColor("#449827"));
			relCircleColor.setBackground(gd);

		} else if (floatBmi>=25.0 && floatBmi<30.0){

			//a8a530

			gd1 = new GradientDrawable();
			gd1.setShape(GradientDrawable.RECTANGLE);
			gd1.setCornerRadius(1.0f);
			gd1.setColor(Color.parseColor("#E0DC40"));
			gd1.setStroke(1, Color.parseColor("#E0DC40"));
			relCircleColorDark.setBackground(gd1);

			gd = new GradientDrawable();
			gd.setShape(GradientDrawable.RECTANGLE);
			gd.setCornerRadius(1.0f);
			gd.setColor(Color.parseColor("#a8a530"));
			gd.setStroke(1, Color.parseColor("#a8a530"));
			relCircleColor.setBackground(gd);

		} else if (floatBmi>=30.0 && floatBmi<35.0){

			//a05822

			gd1 = new GradientDrawable();
			gd1.setShape(GradientDrawable.RECTANGLE);
			gd1.setCornerRadius(1.0f);
			gd1.setColor(Color.parseColor("#D5762D"));
			gd1.setStroke(1, Color.parseColor("#D5762D"));
			relCircleColorDark.setBackground(gd1);

			gd = new GradientDrawable();
			gd.setShape(GradientDrawable.RECTANGLE);
			gd.setCornerRadius(1.0f);
			gd.setColor(Color.parseColor("#a05822"));
			gd.setStroke(1, Color.parseColor("#a05822"));
			relCircleColor.setBackground(gd);

		} else if (floatBmi>=35.0 && floatBmi<40.0){

			//b415bd

			gd1 = new GradientDrawable();
			gd1.setShape(GradientDrawable.RECTANGLE);
			gd1.setCornerRadius(1.0f);
			gd1.setColor(Color.parseColor("#F01CFC"));
			gd1.setStroke(1, Color.parseColor("#F01CFC"));
			relCircleColorDark.setBackground(gd1);

			gd = new GradientDrawable();
			gd.setShape(GradientDrawable.RECTANGLE);
			gd.setCornerRadius(1.0f);
			gd.setColor(Color.parseColor("#b415bd"));
			gd.setStroke(1, Color.parseColor("#b415bd"));
			relCircleColor.setBackground(gd);

		} else {

			gd1 = new GradientDrawable();
			gd1.setShape(GradientDrawable.RECTANGLE);
			gd1.setCornerRadius(1.0f);
			gd1.setColor(Color.parseColor("#EE4031"));
			gd1.setStroke(1, Color.parseColor("#EE4031"));
			relCircleColorDark.setBackground(gd1);

			gd = new GradientDrawable();
			gd.setShape(GradientDrawable.RECTANGLE);
			gd.setCornerRadius(1.0f);
			gd.setColor(Color.parseColor("#b23025"));
			gd.setStroke(1, Color.parseColor("#b23025"));
			relCircleColor.setBackground(gd);

		}

						
	   return vi;
	  }

}
