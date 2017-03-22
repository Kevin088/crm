/**
 * 
 */
package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.model.Contact;
import com.crm.model.Customer;
import com.crm.model.CustomerInfo;
import com.crm.model.Visitlog;
import com.crm.model.easyui.PageHelper;
import com.crm.pojo.CustomerInfoPojo;

/**
 * @author zh
 * 2014-8-2
 */
public interface CustomerInfoMapper {

	//根据名称查询
	public Customer findByName(@Param("customername") String customername);

	//根据id查询
	public Customer getCustomerById(@Param("id") int id);

	//根据 客户id获取其所有联系人
	public List<Contact> getContactById(@Param("customerId") int customerId);
	
	
	//获取所有
	public List<CustomerInfoPojo> getDatagrid();

	//获取总数
	public Long getDatagridTotal(CustomerInfo customerInfo);

	//分页
	public List<CustomerInfoPojo> datagridCustomerInfo(PageHelper page);

	//添加
	public void addCustomerInfo(CustomerInfo customerInfo);

	//修改
	public void editCustomer(Customer customer);
	
	//删除
	public void deleteCustomer(int id);

}
