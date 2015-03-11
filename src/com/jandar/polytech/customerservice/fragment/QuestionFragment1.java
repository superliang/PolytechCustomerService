package com.jandar.polytech.customerservice.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jandar.polytech.customerservice.R;

public class QuestionFragment1 extends Fragment{
	private View localView;
	private TextView tvTest;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		localView = inflater.inflate(R.layout.fragment_question, null);
		tvTest = (TextView) localView.findViewById(R.id.fragment_text);
		Bundle bundle = getArguments();
		tvTest.setText(bundle.getString("question"));
		return localView;
	}
}
