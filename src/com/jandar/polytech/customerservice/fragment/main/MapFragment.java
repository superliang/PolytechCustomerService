package com.jandar.polytech.customerservice.fragment.main;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.jandar.polytech.customerservice.R;
import com.jandar.polytech.customerservice.core.MyMarkerManager;
import com.jandar.polytech.customerservice.util.DataUtil;

public class MapFragment extends Fragment implements
OnMapLoadedCallback{
	private MapView mMapView;
	private TextView tvContent;
	private BaiduMap mBaiduMap;
	private MyMarkerManager oManager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		SDKInitializer.initialize(getActivity().getApplicationContext());
		View view = inflater.inflate(R.layout.fragment_map, null);
		
		mMapView = (MapView) view.findViewById(R.id.bmapView);
		tvContent = (TextView) view.findViewById(R.id.content_textview);
		
		mBaiduMap = mMapView.getMap();
		mBaiduMap.clear();
		LatLng llA = new LatLng(39.956878, 116.308141);
		LatLng[] latLngs = { llA };
		List<OverlayOptions> optionsList = new ArrayList<OverlayOptions>();
		for (int i = 0; i < latLngs.length; i++) {
			BitmapDescriptor bitmap = BitmapDescriptorFactory
					.fromResource(DataUtil.iconId[i]);
			optionsList.add(new MarkerOptions()
					.position(latLngs[i]).icon(bitmap)
					.title(i + ""));
		}
		oManager = new MyMarkerManager(mBaiduMap, tvContent);
		oManager.setData(optionsList);
		mBaiduMap.setOnMarkerClickListener(oManager);
		oManager.addToMap();
		oManager.zoomToSpan();
		mBaiduMap
				.setOnMapLoadedCallback(this);
		return view;
	}

	@Override
	public void onMapLoaded() {
		oManager.zoomToSpan();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		// activity 暂停时同时暂停地图控件
		mMapView.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		// activity 恢复时同时恢复地图控件
		mMapView.onResume();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
	}
}
