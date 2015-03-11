package com.jandar.polytech.customerservice.core;

import com.jandar.polytech.customerservice.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置没有title
	}

	public void initPhone() {
		final TextView ivBottomPhone = (TextView) findViewById(R.id.bottom_phone);
		if (ivBottomPhone != null) {
			ivBottomPhone.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String number = ivBottomPhone.getText().toString();
					Intent intent = new Intent(Intent.ACTION_CALL, Uri
							.parse("tel:" + number));
					startActivity(intent);
				}
			});
		}
	}

	// public abstract void initView();

}
