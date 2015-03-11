package com.jandar.polytech.customerservice.domain;

import com.baidu.mapapi.model.LatLng;

public class FlowPath {
	private LatLng equipLatLng;
	private LatLng personLatLng;
	private LatLng arriveLatLng;
	private int flowNum = 0;
	private Equipment equipInfo;
	private Person personInfo;
	private int dialogContentNum = 0;
	private boolean showPerson = false;
	private boolean flowfinish = false;

	public int getDialogContentNum() {
		return dialogContentNum;
	}

	public void setDialogContentNum(int dialogContentNum) {
		this.dialogContentNum = dialogContentNum;
	}

	public LatLng getEquipLatLng() {
		return equipLatLng;
	}

	public void setEquipLatLng(LatLng equipLatLng) {
		this.equipLatLng = equipLatLng;
	}

	public LatLng getPersonLatLng() {
		return personLatLng;
	}

	public void setPersonLatLng(LatLng personLatLng) {
		this.personLatLng = personLatLng;
	}

	public int getFlowNum() {
		return flowNum;
	}

	public void setFlowNum(int flowNum) {
		this.flowNum = flowNum;
	}

	public Equipment getEquipInfo() {
		return equipInfo;
	}

	public void setEquipInfo(Equipment equipInfo) {
		this.equipInfo = equipInfo;
	}

	public Person getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(Person personInfo) {
		this.personInfo = personInfo;
	}

	public boolean isShowPerson() {
		return showPerson;
	}

	public void setShowPerson(boolean showPerson) {
		this.showPerson = showPerson;
	}

	public LatLng getArriveLatLng() {
		return arriveLatLng;
	}

	public void setArriveLatLng(LatLng arriveLatLng) {
		this.arriveLatLng = arriveLatLng;
	}

	public boolean isFlowfinish() {
		return flowfinish;
	}

	public void setFlowfinish(boolean flowfinish) {
		this.flowfinish = flowfinish;
	}

}
