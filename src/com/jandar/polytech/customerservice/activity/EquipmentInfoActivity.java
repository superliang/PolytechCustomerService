package com.jandar.polytech.customerservice.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.jandar.polytech.customerservice.R;
import com.jandar.polytech.customerservice.core.BaseActivity;

public class EquipmentInfoActivity extends BaseActivity {
	private ImageView ivBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_equipment_info);
		initPhone();
		
		ivBack = (ImageView) findViewById(R.id.back_title_imageView);
		ivBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
