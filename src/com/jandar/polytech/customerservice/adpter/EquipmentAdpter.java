package com.jandar.polytech.customerservice.adpter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.jandar.polytech.customerservice.R;

public class EquipmentAdpter extends BaseAdapter {
	private List<HashMap<String, String>> adpterData;
	private LayoutInflater mInflater;

	public EquipmentAdpter(Context context,
			List<HashMap<String, String>> adpterData) {
		this.mInflater = LayoutInflater.from(context);
		this.adpterData = adpterData;
	}

	@Override
	public int getCount() {
		return adpterData.size();
	}

	@Override
	public Object getItem(int postion) {
		return adpterData.get(postion);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AdpterView adpterView = null;
		String name = adpterData.get(position).get("name");
		if("加".equals(name)){
			convertView = mInflater.inflate(R.layout.listview_equipment_add,
					null);
			return convertView;
		}else{
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.listview_equipment_info,
					null);
			adpterView = new AdpterView();
			adpterView.Id = position;
			adpterView.tvName = (TextView) convertView.findViewById(R.id.equipment_name);
		} else {
			adpterView = (AdpterView) convertView.getTag();
		}
		adpterView.Id = position;
		adpterView.tvName.setText(adpterData.get(position).get("name"));
		return convertView;
		}
	}

	final class AdpterView {
		public int Id;
		public EditText etName;
		public TextView tvName;
		public TextView tvFactory;
		public TextView tvType;
		public TextView tvEquipmentId;
	}
}
