package com.crm.model;

import java.util.Date;

public class CustomerInfo {
	private int id;
	private String name;
	private int telephone;
	private String address;
	private int	 iscompute;
	private int broadband;
	private int broadbandSatisfy;
	private int isBroadbandFusion;
	private float broadbandPrice;
	private String broadbandEndTime;
	private int tv;
	private int tvSatisfy;
	private float tvPrice;
	private String tvEndTime;
	private int district_id;
	private int username_id;

	private Date datetime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public int getIscompute() {
		return iscompute;
	}
	public void setIscompute(int iscompute) {
		this.iscompute = iscompute;
	}
	public int getBroadband() {
		return broadband;
	}
	public void setBroadband(int broadband) {
		this.broadband = broadband;
	}
	public int getBroadbandSatisfy() {
		return broadbandSatisfy;
	}
	public void setBroadbandSatisfy(int broadbandSatisfy) {
		this.broadbandSatisfy = broadbandSatisfy;
	}
	public int getIsBroadbandFusion() {
		return isBroadbandFusion;
	}
	public void setIsBroadbandFusion(int isBroadbandFusion) {
		this.isBroadbandFusion = isBroadbandFusion;
	}
	public float getBroadbandPrice() {
		return broadbandPrice;
	}
	public void setBroadbandPrice(float broadbandPrice) {
		this.broadbandPrice = broadbandPrice;
	}
	public String getBroadbandEndTime() {
		return broadbandEndTime;
	}
	public void setBroadbandEndTime(String broadbandEndTime) {
		this.broadbandEndTime = broadbandEndTime;
	}
	public int getTv() {
		return tv;
	}
	public void setTv(int tv) {
		this.tv = tv;
	}
	public int getTvSatisfy() {
		return tvSatisfy;
	}
	public void setTvSatisfy(int tvSatisfy) {
		this.tvSatisfy = tvSatisfy;
	}
	public float getTvPrice() {
		return tvPrice;
	}
	public void setTvPrice(float tvPrice) {
		this.tvPrice = tvPrice;
	}
	public String getTvEndTime() {
		return tvEndTime;
	}
	public void setTvEndTime(String tvEndTime) {
		this.tvEndTime = tvEndTime;
	}
	
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	public int getUsername_id() {
		return username_id;
	}
	public void setUsername_id(int username_id) {
		this.username_id = username_id;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
