/**
 * 
 */
package com.crm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crm.dao.CustomerInfoMapper;
import com.crm.dao.CustomerMapper;
import com.crm.model.Contact;
import com.crm.model.Customer;
import com.crm.model.CustomerInfo;
import com.crm.model.Visitlog;
import com.crm.model.easyui.PageHelper;
import com.crm.pojo.CustomerInfoPojo;

/**
 * @author zh
 */
/** 
 * Cacheable注解        负责将方法的返回值加入到缓存中 
 * CacheEvict注解     负责清除缓存(它的三个参数与@Cacheable的意思是一样的) 
 * ---------------------------------------------------------------------------------------------------------- 
 * value------缓存位置的名称,不能为空,若使用EHCache则其值为ehcache.xml中的<cache name="myCache"/> 
 * key--------缓存的Key,默认为空(表示使用方法的参数类型及参数值作为key),支持SpEL 
 * condition--只有满足条件的情况才会加入缓存,默认为空(表示全部都加入缓存),支持SpEL 
 * 该注解的源码位于spring-context-*.RELEASE-sources.jar中 
 * Spring针对Ehcache支持的Java源码位于spring-context-support-*.RELEASE-sources.jar中 
 * ---------------------------------------------------------------------------------------------------------- 
 */
@Service
public class CustomerInfoService {

	@Resource
	private CustomerInfoMapper customerInfoMapper;

	public Customer findByName(String name) {
		return customerInfoMapper.findByName(name);
	}
	
    public Customer getUsernameById(int id){  
        return customerInfoMapper.getCustomerById(id);  
    }

	/**
	 * 获取该客户的所有联系人
	 * @return
	 */
	public List<Contact> getContactById(int customerId) {
		return customerInfoMapper.getContactById(customerId);  
	}
	


	/**
	 * 获取总数
	 * @param user
	 * @return
	 */
	public Long getDatagridTotal(CustomerInfo customerInfo) {
		return customerInfoMapper.getDatagridTotal(customerInfo);
	}

	/**
	 * 获列表 分页
	 * @param page
	 * @return
	 */
	public List<CustomerInfoPojo> datagridCustomer(PageHelper page) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		return customerInfoMapper.datagridCustomerInfo(page);  
	}

	//添加
	public void addCustomer(Customer customer){
		customerInfoMapper.addCustomer(customer);
	};

	//修改
	public void editCustomer(Customer customer){
		customerInfoMapper.editCustomer(customer);
	};
	
	//删除
	public void deleteCustomer(int id){
		customerInfoMapper.deleteCustomer(id);
	};
    
}
