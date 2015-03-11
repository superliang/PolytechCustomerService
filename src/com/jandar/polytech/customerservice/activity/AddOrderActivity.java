package com.jandar.polytech.customerservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.jandar.polytech.customerservice.R;
import com.jandar.polytech.customerservice.util.zxing.CaptureActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class AddOrderActivity extends BaseLeftActivity {
	private ImageView ivLeftMenu;
	private ImageView ivScan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_order);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		initPhone();
		
		ivLeftMenu = (ImageView) findViewById(R.id.leftMenu_title_imageView);
		ivScan = (ImageView) findViewById(R.id.scan_title_imageView);
		
		ivScan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO 跳转到扫描界面，但是由于测试需要直接跳转到添加服务单页面
				Intent intent = new Intent(
						AddOrderActivity.this, CaptureActivity.class);
				startActivity(intent);
			}
		});
		
		ivLeftMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});
		
	}
	
	

}
