package com.crm.pojo;

import com.crm.model.easyui.PageHelper;

public class CustomerInfoSearchPojo extends PageHelper{
	private String userName;
	private String name;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
