package com.jandar.polytech.customerservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jandar.polytech.customerservice.R;
import com.jandar.polytech.customerservice.core.BaseActivity;
import com.jandar.polytech.customerservice.util.zxing.CaptureActivity;

public class LoadingActivity extends BaseActivity {
	private boolean isTemp = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);

		if (isTemp) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent intent = new Intent(LoadingActivity.this,
							CaptureActivity.class);
					startActivity(intent);
				}
			}, 500);
			finish();
		} else {
		}
	}

}
