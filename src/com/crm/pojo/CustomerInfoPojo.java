package com.crm.pojo;

import com.crm.model.CustomerInfo;

public class CustomerInfoPojo extends CustomerInfo{
	private String userName;
	private String districtName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
}
