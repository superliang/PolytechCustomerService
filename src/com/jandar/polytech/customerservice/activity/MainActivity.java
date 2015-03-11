package com.jandar.polytech.customerservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jandar.polytech.customerservice.R;
import com.jandar.polytech.customerservice.core.BaseActivity;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	public void startClick(View view){
		Intent intent = new Intent(MainActivity.this,AddOrderActivity.class);
		startActivity(intent);
		finish();
	}

}
