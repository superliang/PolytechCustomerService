package com.jandar.polytech.customerservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jandar.polytech.customerservice.activity.AddOrderActivity;
import com.jandar.polytech.customerservice.activity.BaseLeftActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class OpinionActivity extends BaseLeftActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opinion);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		initPhone();
		
		
	}
	
	public void opinionCanelClick(View view){
		Intent intent = new Intent(OpinionActivity.this,AddOrderActivity.class);
		startActivity(intent);
	}
	
	public void opinionSublimeClick(View view){
		Intent intent = new Intent(OpinionActivity.this,AddOrderActivity.class);
		startActivity(intent);
	}

}
