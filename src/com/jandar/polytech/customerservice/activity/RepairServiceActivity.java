package com.jandar.polytech.customerservice.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.jandar.polytech.customerservice.R;
import com.jandar.polytech.customerservice.core.MyMarkerManager;
import com.jandar.polytech.customerservice.domain.Equipment;
import com.jandar.polytech.customerservice.domain.FlowPath;
import com.jandar.polytech.customerservice.domain.Person;
import com.jandar.polytech.customerservice.util.DataUtil;
import com.jandar.polytech.customerservice.util.zxing.CaptureActivity;
import com.jandar.polytech.customerservice.view.MyDialog;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class RepairServiceActivity extends BaseLeftActivity implements
		OnMapLoadedCallback, OnClickListener {
	private MapView mMapView;
	private TextView tvContent;
	private BaiduMap mBaiduMap;
	private TextView ivRefresh;
	private ImageView ivLeftMenu;
	private ImageView ivScan;

	private MyMarkerManager oManager;
	private String[] titles = { "XX先生/小姐，您好！", "XX先生/小姐，您好！", "屏幕故障", "吞卡故障",
			"其他" };
	private String[] messages = { "  您于XX日XX时XX分报修的故障XXXXX，设备是否已正常运行",
			"  您与XX日XX时XX分报修的故障XXXXX,XX已和您预约到XX时XX分，请您核对时间是否准确？",
			"  预约时间有误?请点击下方按钮，让我们重新约个时间吧！",
			"  您与XX日XX时XX分报修的故障XXXXX,XX已于XX日XX时XX分到达现场，请核实工程师信息，谢谢！",
			"  您与XX日XX时XX分报修的故障XXXXX，XX已修复故障，请您对他的服务做出评价。您的满意是我们进步最大的动力" };
	private List<FlowPath> flowPaths = new ArrayList<FlowPath>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_repair);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		createData(flowPaths);

		mMapView = (MapView) findViewById(R.id.bmapView);
		tvContent = (TextView) findViewById(R.id.content_textview);
		ivRefresh = (TextView) findViewById(R.id.bottom_refresh);
		ivLeftMenu = (ImageView) findViewById(R.id.leftMenu_title_imageView);
		ivScan = (ImageView) findViewById(R.id.scan_title_imageView);

		ivScan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 跳转到扫描界面，但是由于测试需要直接跳转到添加服务单页面
				Intent intent = new Intent(RepairServiceActivity.this,
						CaptureActivity.class);
				startActivity(intent);
			}
		});
		ivRefresh.setOnClickListener(this);
		ivLeftMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});

		mBaiduMap = mMapView.getMap();
	}

	/**
	 * 弹出试覆盖物
	 */
	public void initPopOverlay(Marker marker, final Equipment equip) {
		final LatLng ll = marker.getPosition();
		Point p = mBaiduMap.getProjection().toScreenLocation(ll);

		p.y -= 45;
		LatLng latLng = mBaiduMap.getProjection().fromScreenLocation(p);

		View view = getLayoutInflater().inflate(R.layout.popview_child, null);
		TextView tvName = (TextView) view.findViewById(R.id.equipName);
		TextView tvNo = (TextView) view.findViewById(R.id.equipNo);
		TextView tvTime = (TextView) view.findViewById(R.id.equipTime);
		tvName.setText(equip.getEquipName());
		tvNo.setText(equip.getEquipNo());
		tvTime.setText(equip.getEquipTime());
		OnInfoWindowClickListener listener = new OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick() {
				mBaiduMap.hideInfoWindow();
				Intent intent = new Intent(RepairServiceActivity.this,
						EquipmentInfoActivity.class);
				startActivity(intent);
			}
		};

		// 创建InfoWindow
		InfoWindow mInfoWindow = new InfoWindow(view, latLng, listener);

		// 显示InfoWindow
		mBaiduMap.showInfoWindow(mInfoWindow);

	}

	public void initPopOverlayPerson(Marker marker, final Person equip) {
		final LatLng ll = marker.getPosition();
		Point p = mBaiduMap.getProjection().toScreenLocation(ll);

		p.y -= 45;
		LatLng latLng = mBaiduMap.getProjection().fromScreenLocation(p);

		View view = getLayoutInflater().inflate(R.layout.popview_child, null);
		TextView tvName = (TextView) view.findViewById(R.id.equipName);
		TextView tvPhone = (TextView) view.findViewById(R.id.equipNo);
		TextView tvTime = (TextView) view.findViewById(R.id.equipTime);
		tvName.setText(equip.getName());
		tvPhone.setText(equip.getTelephone());
		tvTime.setVisibility(View.GONE);
		OnInfoWindowClickListener listener = new OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick() {
				mBaiduMap.hideInfoWindow();
				Intent intent = new Intent(RepairServiceActivity.this,
						PersonInfoActivity.class);
				startActivity(intent);
			}
		};

		// 创建InfoWindow
		InfoWindow mInfoWindow = new InfoWindow(view, latLng, listener);

		// 显示InfoWindow
		mBaiduMap.showInfoWindow(mInfoWindow);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		SharedPreferences sp = getSharedPreferences("polytech", MODE_APPEND);
		boolean haveLine = sp.getBoolean("haveLine", false);
		if (haveLine) {
			boolean line1 = sp.getBoolean("line1", false);
			boolean line2 = sp.getBoolean("line2", false);
			if (line1) {
				createFlow1();
				flowPaths.get(0).setFlowfinish(line1);
			}
			if (line2) {
				createFlow2();
				flowPaths.get(1).setFlowfinish(line2);
			}
		} else {
			Intent intent = new Intent(RepairServiceActivity.this,
					AddOrderActivity.class);
			startActivity(intent);
			finish();
		}
		mBaiduMap.clear();
		List<OverlayOptions> optionsList = new ArrayList<OverlayOptions>();
		for (int i = 0; i < flowPaths.size(); i++) {
			FlowPath flowPath = flowPaths.get(i);
			if (flowPath.isFlowfinish()) {
				BitmapDescriptor bitmap = BitmapDescriptorFactory
						.fromResource(DataUtil.iconId[i]);
				optionsList.add(new MarkerOptions()
						.position(flowPath.getEquipLatLng()).icon(bitmap)
						.title(i + ""));
				if (flowPath.isShowPerson()) {
					BitmapDescriptor bitmapPerson = BitmapDescriptorFactory
							.fromResource(DataUtil.iconPersonId[i]);
					optionsList.add(new MarkerOptions()
							.position(flowPath.getPersonLatLng())
							.icon(bitmapPerson).title(i + 2 + ""));
				}
			}
		}

		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				return false;
			}

			@Override
			public void onMapClick(LatLng latLng) {
				mBaiduMap.hideInfoWindow();
			}
		});

		oManager = new MyMarkerManager(mBaiduMap, tvContent);
		oManager.setData(optionsList);
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				oManager.onMarkerClick(marker);
				int latLngNum = Integer.parseInt(marker.getTitle());
				if (latLngNum < 2) {
					initPopOverlay(marker, flowPaths.get(latLngNum)
							.getEquipInfo());
				} else {
					initPopOverlayPerson(marker, flowPaths.get(latLngNum - 2)
							.getPersonInfo());
				}
				return false;
			}
		});
		oManager.addToMap();
		mBaiduMap.setOnMapLoadedCallback(this);

		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}

	@Override
	public void onMapLoaded() {
		// 完成地图加载后再通过zoomToSpan方法才能获得最佳的展示效果
		oManager.zoomToSpan();
	}

	@Override
	public void onClick(View v) {
		if (flowPaths.get(0).isFlowfinish()) {
			refreshStep(flowPaths.get(0), 0);
		} else {
			refreshStep(flowPaths.get(1), 1);
		}

	}

	private void refreshStep(final FlowPath flowPath, final int position) {
		MyDialog.Builder builder = null;
		MyDialog dialog = null;
		switch (flowPath.getFlowNum()) {
		// 电话报修
		case 0:
			builder = new MyDialog.Builder(this);
			builder.setTitle(titles[1]);
			builder.setMessage(messages[flowPath.getDialogContentNum()]);
			builder.setPositiveButton("否",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			builder.setNegativeButton("是",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							MyDialog.Builder builder2 = new MyDialog.Builder(
									RepairServiceActivity.this);
							builder2.setTitle(titles[1]);
							builder2.setMessage(messages[4]);
							builder2.setPositiveButton("跳过",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											Intent intent = new Intent(
													RepairServiceActivity.this,
													AddOrderActivity.class);
											RepairServiceActivity.this
													.startActivity(intent);
											RepairServiceActivity.this.finish();
										}
									});
							builder2.setNegativeButton("评价",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											Intent intent = new Intent(
													RepairServiceActivity.this,
													EvaluateActivity.class);
											RepairServiceActivity.this
													.startActivity(intent);
											RepairServiceActivity.this.finish();
										}
									});
							MyDialog dialog2 = builder2.create();
							dialog2.setCancelable(false);
							dialog2.show();
						}
					});
			dialog = builder.create();
			dialog.setCancelable(false);
			dialog.show();
			flowPath.setDialogContentNum(flowPath.getDialogContentNum() + 1);
			flowPath.setFlowNum(flowPath.getFlowNum() + 1);
			break;
		// 工程师和客户预约时间
		case 1:
			builder = new MyDialog.Builder(this);
			builder.setTitle(titles[1]);
			builder.setMessage(messages[flowPath.getDialogContentNum()]);
			builder.setPositiveButton("否",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog1, int which) {
							dialog1.dismiss();
							int messageNum = flowPath.getFlowNum() + 1;
							MyDialog.Builder builder2 = new MyDialog.Builder(
									RepairServiceActivity.this);
							builder2.setTitle(titles[1]);
							builder2.setMessage(messages[messageNum]);
							builder2.setPhoneButton("点击预约",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									});
							MyDialog dialog2 = builder2.create();
							dialog2.setCancelable(false);
							dialog2.show();
						}
					});
			builder.setNegativeButton("是",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							flowPath.setDialogContentNum(flowPath
									.getDialogContentNum() + 1);
							flowPath.setFlowNum(flowPath.getFlowNum() + 1);
							flowPath.setShowPerson(true);
						}
					});
			dialog = builder.create();
			dialog.setCancelable(false);
			dialog.show();
			break;
		// 工程师出发
		case 2:
			mBaiduMap.clear();
			List<OverlayOptions> optionsList1 = new ArrayList<OverlayOptions>();
			for (int i = 0; i < flowPaths.size(); i++) {
				FlowPath tempFlow = flowPaths.get(i);
				if (tempFlow.isFlowfinish()) {
					BitmapDescriptor bitmap = BitmapDescriptorFactory
							.fromResource(DataUtil.iconId[i]);
					optionsList1.add(new MarkerOptions()
							.position(tempFlow.getEquipLatLng()).icon(bitmap)
							.title(i + ""));
					if (tempFlow.isShowPerson()) {
						BitmapDescriptor bitmapPerson = BitmapDescriptorFactory
								.fromResource(DataUtil.iconPersonId[i]);
						optionsList1.add(new MarkerOptions()
								.position(tempFlow.getPersonLatLng())
								.icon(bitmapPerson).title(i + 2 + ""));
					}
				}
			}
			oManager = new MyMarkerManager(mBaiduMap, tvContent);
			oManager.setData(optionsList1);
			mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

				@Override
				public boolean onMarkerClick(Marker marker) {
					oManager.onMarkerClick(marker);
					int latLngNum = Integer.parseInt(marker.getTitle());
					if (latLngNum < 2) {
						initPopOverlay(marker, flowPaths.get(latLngNum)
								.getEquipInfo());
					} else {
						initPopOverlayPerson(marker,
								flowPaths.get(latLngNum - 2).getPersonInfo());
					}
					return false;
				}
			});
			oManager.addToMap();
			oManager.zoomToSpan();
			mBaiduMap.setOnMapLoadedCallback(RepairServiceActivity.this);
			flowPath.setFlowNum(flowPath.getFlowNum() + 1);
			break;

		case 3:
			mBaiduMap.clear();
			List<OverlayOptions> optionsList2 = new ArrayList<OverlayOptions>();
//			SharedPreferences sp = getSharedPreferences("polytech", MODE_APPEND);
			// int moveFlow = sp.getInt("mainLine", 0);
			// for (int i = 0; i < flowPaths.size(); i++) {
			// FlowPath tempFlow = flowPaths.get(i);
			// if (tempFlow.isFlowfinish()) {
			// BitmapDescriptor bitmap = BitmapDescriptorFactory
			// .fromResource(DataUtil.iconId[i]);
			// optionsList2.add(new MarkerOptions()
			// .position(tempFlow.getEquipLatLng()).icon(bitmap)
			// .title(i + ""));
			// if (tempFlow.isShowPerson()) {
			// BitmapDescriptor bitmapPerson = BitmapDescriptorFactory
			// .fromResource(DataUtil.iconPersonId[i]);
			// if (moveFlow == (i + 1)) {
			// optionsList2.add(new MarkerOptions()
			// .position(tempFlow.getArriveLatLng())
			// .icon(bitmapPerson).title(i + 2 + ""));
			// } else {
			// optionsList2.add(new MarkerOptions()
			// .position(tempFlow.getPersonLatLng())
			// .icon(bitmapPerson).title(i + 2 + ""));
			// }
			// }
			// }
			// }

			for (int i = 0; i < flowPaths.size(); i++) {
				FlowPath tempFlow = flowPaths.get(i);
				if (tempFlow.isFlowfinish()) {
					BitmapDescriptor bitmap = BitmapDescriptorFactory
							.fromResource(DataUtil.iconId[i]);
					optionsList2.add(new MarkerOptions()
							.position(tempFlow.getEquipLatLng()).icon(bitmap)
							.title(i + ""));
					if (tempFlow.isShowPerson()) {
						BitmapDescriptor bitmapPerson = BitmapDescriptorFactory
								.fromResource(DataUtil.iconPersonId[i]);
						optionsList2.add(new MarkerOptions()
								.position(tempFlow.getArriveLatLng())
								.icon(bitmapPerson).title(i + 2 + ""));
					}
				}
			}
			oManager = new MyMarkerManager(mBaiduMap, tvContent);
			oManager.setData(optionsList2);
			mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

				@Override
				public boolean onMarkerClick(Marker marker) {
					oManager.onMarkerClick(marker);
					int latLngNum = Integer.parseInt(marker.getTitle());
					if (latLngNum < 2) {
						initPopOverlay(marker, flowPaths.get(latLngNum)
								.getEquipInfo());
					} else {
						initPopOverlayPerson(marker,
								flowPaths.get(latLngNum - 2).getPersonInfo());
					}
					return false;
				}
			});
			oManager.addToMap();
			oManager.zoomToSpan();
			mBaiduMap.setOnMapLoadedCallback(RepairServiceActivity.this);
			flowPath.setDialogContentNum(flowPath.getDialogContentNum() + 1);
			flowPath.setFlowNum(flowPath.getFlowNum() + 1);
			break;
		case 4:
			builder = new MyDialog.Builder(this);
			builder.setTitle(titles[1]);
			builder.setMessage(messages[flowPath.getDialogContentNum()]);
			builder.setNegativeButton("工程师已到达",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			dialog = builder.create();
			dialog.setCancelable(false);
			dialog.show();
			flowPath.setDialogContentNum(flowPath.getDialogContentNum() + 1);
			flowPath.setFlowNum(flowPath.getFlowNum() + 1);
			break;
		case 5:
			builder = new MyDialog.Builder(this);
			builder.setTitle(titles[1]);
			builder.setMessage("  您于XX日XX时XX分报修的故障XXXXX，设备是否已正常运行");
			builder.setPositiveButton("否",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							MyDialog.Builder builder2 = new MyDialog.Builder(
									RepairServiceActivity.this);
							builder2.setTitle(titles[1]);
							builder2.setMessage(messages[flowPath
									.getDialogContentNum()]);
							builder2.setPositiveButton("跳过",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											Intent intent = new Intent(
													RepairServiceActivity.this,
													AddOrderActivity.class);
											RepairServiceActivity.this
													.startActivity(intent);
											RepairServiceActivity.this.finish();
										}
									});
							builder2.setNegativeButton("评价",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											SharedPreferences sp = getSharedPreferences(
													"polytech", MODE_APPEND);
											if (position == 1) {
												sp.edit()
														.putBoolean("line1",
																false).commit();
												if (sp.getBoolean("line2",
														false)) {
													sp.edit()
															.putInt("mainLine",
																	1).commit();
												} else {
													sp.edit()
															.putInt("mainLine",
																	0).commit();
													sp.edit()
															.putBoolean(
																	"haveLine",
																	false)
															.commit();
												}
											} else {
												sp.edit()
														.putBoolean("line2",
																false).commit();
												if (sp.getBoolean("line1",
														false)) {
													sp.edit()
															.putInt("mainLine",
																	1).commit();
												} else {
													sp.edit()
															.putInt("mainLine",
																	0).commit();
													sp.edit()
															.putBoolean(
																	"haveLine",
																	false)
															.commit();
												}
											}
											Intent intent = new Intent(
													RepairServiceActivity.this,
													EvaluateActivity.class);
											RepairServiceActivity.this
													.startActivity(intent);
											RepairServiceActivity.this.finish();
										}
									});
							MyDialog dialog2 = builder2.create();
							dialog2.setCancelable(false);
							dialog2.show();
						}
					});
			builder.setNegativeButton("是",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							MyDialog.Builder builder2 = new MyDialog.Builder(
									RepairServiceActivity.this);
							builder2.setTitle(titles[1]);
							builder2.setMessage(messages[flowPath
									.getDialogContentNum()]);
							builder2.setPositiveButton("跳过",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											Intent intent = new Intent(
													RepairServiceActivity.this,
													AddOrderActivity.class);
											RepairServiceActivity.this
													.startActivity(intent);
											RepairServiceActivity.this.finish();
										}
									});
							builder2.setNegativeButton("评价",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											SharedPreferences sp = getSharedPreferences(
													"polytech", MODE_APPEND);
											if (position == 1) {
												sp.edit()
														.putBoolean("line1",
																false).commit();
												if (sp.getBoolean("line2",
														false)) {
													sp.edit()
															.putInt("mainLine",
																	1).commit();
												} else {
													sp.edit()
															.putInt("mainLine",
																	0).commit();
													sp.edit()
															.putBoolean(
																	"haveLine",
																	false)
															.commit();
												}
											} else {
												sp.edit()
														.putBoolean("line2",
																false).commit();
												if (sp.getBoolean("line1",
														false)) {
													sp.edit()
															.putInt("mainLine",
																	1).commit();
												} else {
													sp.edit()
															.putInt("mainLine",
																	0).commit();
													sp.edit()
															.putBoolean(
																	"haveLine",
																	false)
															.commit();
												}
											}
											Intent intent = new Intent(
													RepairServiceActivity.this,
													EvaluateActivity.class);
											RepairServiceActivity.this
													.startActivity(intent);
											RepairServiceActivity.this.finish();
										}
									});
							MyDialog dialog2 = builder2.create();
							dialog2.setCancelable(false);
							dialog2.show();
						}
					});
			dialog = builder.create();
			dialog.setCancelable(false);
			dialog.show();

			flowPath.setFlowNum(flowPath.getFlowNum() + 1);
			break;
		case 6:

			break;
		}
	}

	private void createFlow1() {
		FlowPath flowPath1 = new FlowPath();
		LatLng llA = new LatLng(39.956878, 116.308141);
		LatLng llC = new LatLng(39.939723, 116.425541);
		LatLng llE = new LatLng(39.957078, 116.308241);
		flowPath1.setEquipLatLng(llA);
		flowPath1.setArriveLatLng(llE);
		flowPath1.setPersonLatLng(llC);
		Equipment equipInfo1 = new Equipment();
		equipInfo1.setEquipName("设备名称：OKI 2001S");
		equipInfo1.setEquipNo("设备编号：0248");
		equipInfo1.setEquipTime("报修时间：2014-9-29");
		flowPath1.setEquipInfo(equipInfo1);
		Person personInfo1 = new Person();
		personInfo1.setName("姓名：朱强");
		personInfo1.setTelephone("电话：15098765432");
		flowPath1.setPersonInfo(personInfo1);
		flowPaths.add(0, flowPath1);
	}

	private void createFlow2() {
		LatLng llB = new LatLng(39.942821, 116.369199);
		LatLng llD = new LatLng(39.906965, 116.401394);
		LatLng llF = new LatLng(39.939923, 116.425741);
		FlowPath flowPath2 = new FlowPath();
		flowPath2.setEquipLatLng(llB);
		flowPath2.setPersonLatLng(llD);
		flowPath2.setArriveLatLng(llF);
		Equipment equipInfo2 = new Equipment();
		equipInfo2.setEquipName("设备名称：OKI 2002S");
		equipInfo2.setEquipNo("设备编号：0223");
		equipInfo2.setEquipTime("报修时间：2014-9-29");
		flowPath2.setEquipInfo(equipInfo2);
		Person personInfo2 = new Person();
		personInfo2.setName("姓名：王强");
		personInfo2.setTelephone("电话：15012345678");
		flowPath2.setPersonInfo(personInfo2);
		flowPaths.add(1, flowPath2);
	}

	private void createData(List<FlowPath> flowPaths) {
		LatLng llA = new LatLng(39.956878, 116.308141);
		LatLng llB = new LatLng(39.942821, 116.369199);
		LatLng llC = new LatLng(39.939723, 116.425541);
		LatLng llD = new LatLng(39.906965, 116.401394);
		LatLng llE = new LatLng(39.957078, 116.308241);
		LatLng llF = new LatLng(39.939923, 116.425741);
		FlowPath flowPath1 = new FlowPath();
		FlowPath flowPath2 = new FlowPath();
		flowPath1.setEquipLatLng(llA);
		flowPath2.setEquipLatLng(llB);
		flowPath1.setArriveLatLng(llE);
		flowPath1.setPersonLatLng(llC);
		flowPath2.setPersonLatLng(llD);
		flowPath2.setArriveLatLng(llF);

		Equipment equipInfo1 = new Equipment();
		equipInfo1.setEquipName("设备名称：OKI 2001S");
		equipInfo1.setEquipNo("设备编号：0248");
		equipInfo1.setEquipTime("报修时间：2014-9-29");
		flowPath1.setEquipInfo(equipInfo1);
		Equipment equipInfo2 = new Equipment();
		equipInfo2.setEquipName("设备名称：OKI 2002S");
		equipInfo2.setEquipNo("设备编号：0223");
		equipInfo2.setEquipTime("报修时间：2014-9-29");
		flowPath2.setEquipInfo(equipInfo2);

		Person personInfo1 = new Person();
		personInfo1.setName("姓名：朱强");
		personInfo1.setTelephone("电话：15098765432");
		flowPath1.setPersonInfo(personInfo1);
		Person personInfo2 = new Person();
		personInfo2.setName("姓名：王强");
		personInfo2.setTelephone("电话：15012345678");
		flowPath2.setPersonInfo(personInfo2);

		flowPaths.add(flowPath1);
		flowPaths.add(flowPath2);
	}

}
