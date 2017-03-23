/**
 * 
 */
package com.crm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.crm.dao.DistrictMapper;
import com.crm.model.District;
import com.crm.model.SysMenu;
import com.crm.model.User;
import com.crm.model.easyui.PageHelper;
import com.crm.pojo.CustomerInfoPojo;
import com.crm.pojo.CustomerInfoSearchPojo;

/**
 * @author zh
 * 2014-8-2
 */
@Service
public class DistrictService {

	@Resource
	private DistrictMapper districtMapper;
	
	//分页
	public List<District> datagridDistrict(PageHelper page) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());		
		return districtMapper.datagridDistrict(page);  
	}
	//分页
	public long datagridTotal() {	
		return districtMapper.getDatagridTotal();  
	}
    public void deleteDistrict(long id){
    	districtMapper.deleteDistrict(id);
    }
	
}
