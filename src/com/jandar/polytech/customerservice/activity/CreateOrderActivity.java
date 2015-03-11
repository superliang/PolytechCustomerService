package com.jandar.polytech.customerservice.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.jandar.polytech.customerservice.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class CreateOrderActivity extends BaseLeftActivity {
	private Button btnConfirm;
	private Button btnCancel;
	private Spinner sError;
	private Spinner sEngier;

	private String[] itemError = { "电脑死机", "钞箱损坏" };
	private String[] itemEngier = { "无", "张三", "李四" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_order);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		initPhone();

		btnConfirm = (Button) findViewById(R.id.creatOrder_confirm_button);
		btnCancel = (Button) findViewById(R.id.creatOrder_cancel_button);
		sError = (Spinner) findViewById(R.id.createOrder_error_spinner);
		sEngier = (Spinner) findViewById(R.id.createOrder_enginer_spinner);

		sError.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itemError));
		sEngier.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itemEngier));
		
		btnConfirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences sp = getSharedPreferences("polytech",
						MODE_APPEND);
				boolean haveLine = sp.getBoolean("haveLine", false);
				if (haveLine) {
					boolean line1 = sp.getBoolean("lien1", false);
					if (line1) {
						sp.edit().putBoolean("line2", true).commit();
						sp.edit().putInt("mainLine", 1).commit();
					} else {
						sp.edit().putBoolean("line1", true).commit();
						sp.edit().putInt("mainLine", 2).commit();
					}
				} else {
					sp.edit().putBoolean("haveLine", true).commit();
					sp.edit().putInt("mainLine", 1).commit();
					sp.edit().putBoolean("line1", true).commit();
					sp.edit().putBoolean("line2", false).commit();
				}
				Intent intent = new Intent(CreateOrderActivity.this,
						RepairServiceActivity.class);
				startActivity(intent);
			}
		});
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences sp = getSharedPreferences("polytech",
						MODE_APPEND);
				boolean haveLine = sp.getBoolean("haveLine", false);
				if (haveLine) {
					Intent intent = new Intent(CreateOrderActivity.this,
							RepairServiceActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(CreateOrderActivity.this,
							AddOrderActivity.class);
					startActivity(intent);
				}
				CreateOrderActivity.this.finish();
			}
		});
	}

}
