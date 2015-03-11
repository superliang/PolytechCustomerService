package com.jandar.polytech.customerservice.core;

import java.util.List;

import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.overlayutil.OverlayManager;

public class MyMarkerManager extends OverlayManager {
	private BaiduMap baiduMap;
	private TextView tvContent;
	private List<OverlayOptions> optionsList;

	public MyMarkerManager(BaiduMap baiduMap, TextView textView) {
		super(baiduMap);
		this.baiduMap = baiduMap;
		this.tvContent = textView;
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(marker
				.getPosition());
		baiduMap.animateMapStatus(u);

		int target = Integer.parseInt(marker.getTitle());

		switch (target) {
		case 0:
			tvContent.setText("浙江省杭州市西湖区文三路20号");
			break;
		case 1:
			tvContent.setText("浙江省杭州市下城区凤起路18号");
			break;
		case 2:
			tvContent.setText("浙江省杭州市拱墅区湖州街108号");
			break;
		case 3:
			tvContent.setText("浙江省杭州市上城区解放路110号");
			break;
		}

		return true;
	}

	@Override
	public List<OverlayOptions> getOverlayOptions() {
		return optionsList;
	}

	public void setData(List<OverlayOptions> optionsList) {
		this.optionsList = optionsList;
	}

}
