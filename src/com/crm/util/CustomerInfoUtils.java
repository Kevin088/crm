package com.crm.util;

public class CustomerInfoUtils {
	
	
	
	//是否有电脑
	public static String  formatIsOrNo(int val){
		String a;
		if(val==1)
			a="是";
		else
			a="否";
		return a;
	}
	//宽带运营商  电信、移动、联通、无
	public static String  formatBroadband(int val){
		String a = null;
		switch(val){
			case 1:
				a="电信";	
			break;
			case 2:
				a="移动";	
			break;
			case 3:
				a="联通";	
			break;
			case 4:
				a="无";	
			break;
		}
		return a;
	}
		//宽带满意度 
	public static String  formatBroadbandSatisfy(int val){
		String a = null;
		switch(val){
			case 1:
				a="很满意";	
			break;
			case 2:
				a="比较满意";	
			break;
			case 3:
				a="一般";	
			break;
			case 4:
				a="不满意";	
			break;
		}
		return a;
	}
		
	//电视运营商 电信、移动、联通、广电、卫星电视接收器、无
	public static String  formatTv(int val){
		String a = null;
		switch(val){
			case 1:
				a="电信";	
			break;
			case 2:
				a="移动";	
			break;
			case 3:
				a="联通";	
			break;
			case 4:
				a="广电";	
			break;
			case 5:
				a="卫星电视接收器";	
			break;
			case 6:
				a="无";	
			break;
		}
		return a;
	}
}
