package com.crm.pojo;

import com.crm.model.easyui.Json;

public class AppJson extends Json{
	private int pageIndex;
	private int pageCount;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
