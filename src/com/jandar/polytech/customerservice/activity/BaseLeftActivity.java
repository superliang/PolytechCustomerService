package com.jandar.polytech.customerservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jandar.polytech.customerservice.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public abstract class BaseLeftActivity extends Activity {
	protected SlidingMenu slidingMenu = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置没有title
		initSlidingMenu();
	}

	public void initSlidingMenu() {
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN); // 触摸边界拖出菜单
		slidingMenu.setMenu(R.layout.leftmenu_layout);
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 将抽屉菜单与主页面关联起来
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
}
