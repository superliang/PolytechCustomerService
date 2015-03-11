package com.jandar.polytech.customerservice.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.jandar.polytech.customerservice.R;
import com.jandar.polytech.customerservice.core.BaseActivity;

public class PersonInfoActivity extends BaseActivity {
	private ImageView ivPhone;
	private TextView tvPhoneNum;
	private ImageView ivBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_info);
		
		ivPhone = (ImageView) findViewById(R.id.phone_title_imageView);
		tvPhoneNum = (TextView) findViewById(R.id.person_phoneNum);
		
		ivPhone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String number = tvPhoneNum.getText().toString();
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number));  
                startActivity(intent); 
			}
		});
		ivBack = (ImageView) findViewById(R.id.back_title_imageView);
		ivBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}


}
