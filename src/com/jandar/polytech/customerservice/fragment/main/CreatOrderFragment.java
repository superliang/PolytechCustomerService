package com.jandar.polytech.customerservice.fragment.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.jandar.polytech.customerservice.R;
import com.jandar.polytech.customerservice.activity.RepairServiceActivity;

public class CreatOrderFragment extends Fragment{
	private Button btnConfirmOrder;
	private Activity activity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_creatorder, null);
		activity = getActivity();
		btnConfirmOrder = (Button) view.findViewById(R.id.creatOrder_confirm_button);
		
		btnConfirmOrder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				getFragmentManager().beginTransaction().replace(R.id.main_content_layout, new MapFragment()).commit();
//				Intent intent  = new Intent(activity,RepairServiceActivity.class);
//				activity.startActivity(intent);
			}
		});
		return view;
	}
}
