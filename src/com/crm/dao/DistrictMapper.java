/**
 * 
 */
package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.model.District;
import com.crm.model.SysMenu;
import com.crm.model.User;
import com.crm.model.easyui.PageHelper;
import com.crm.pojo.CustomerInfoPojo;

/**
 * @author xll
 * 2014-8-2
 */
public interface DistrictMapper {
	
	public void addDistrict(District district);

	public void updateDistrict(District district);
	
	public void deleteDistrict(long id);
	//分页
	public List<District> datagridDistrict(PageHelper page);
	//获取所有
	public List<District> getDatagrid();
	public long getDatagridTotal();
}
