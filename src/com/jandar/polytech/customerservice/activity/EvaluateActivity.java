package com.jandar.polytech.customerservice.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import com.jandar.polytech.customerservice.OpinionActivity;
import com.jandar.polytech.customerservice.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class EvaluateActivity extends BaseLeftActivity {
	private RatingBar rbRating1;
	private RatingBar rbRating2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluate);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		initPhone();
		
		rbRating1 = (RatingBar) findViewById(R.id.ratingBar1);
		rbRating2 = (RatingBar) findViewById(R.id.ratingBar2);
		
		rbRating1.setStepSize((float)1.0F);
		rbRating2.setStepSize((float)1.0F);
		
	}

	public void finishEvaluteClick(View view){
		SharedPreferences sp = getSharedPreferences("polytech", MODE_APPEND);
		boolean haveLine = sp.getBoolean("haveLine", false);
		if(rbRating1.getRating()<3.0||rbRating2.getRating()<3.0){
			Intent intent = new Intent(EvaluateActivity.this,OpinionActivity.class);
			startActivity(intent);
		}else{
			Intent intent = new Intent(EvaluateActivity.this,AddOrderActivity.class);
			startActivity(intent);
		}
//		if(haveLine){
//			Intent intent = new Intent(EvaluateActivity.this,RepairServiceActivity.class);
//			startActivity(intent);
//		}else{
//			Intent intent = new Intent(EvaluateActivity.this,AddOrderActivity.class);
//			startActivity(intent);
//		}
//		finish();
		
	}
}
