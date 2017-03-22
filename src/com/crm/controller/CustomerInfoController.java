/**
 * 
 */
package com.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.Customer;
import com.crm.model.CustomerInfo;
import com.crm.model.easyui.DataGrid;
import com.crm.model.easyui.Json;
import com.crm.model.easyui.PageHelper;
import com.crm.pojo.CustomerInfoPojo;
import com.crm.service.CustomerInfoService;

/**
 * @author zh
 */
@Controller
public class CustomerInfoController {
	
	private final Logger log = LoggerFactory.getLogger(CustomerInfoController.class);
	
	@Resource
	private CustomerInfoService customerInfoService;
	
	/**
	 * 跳转到客户表格页面
	 * @return
	 */
	@RequestMapping(value = "/customerinfo/list",method = RequestMethod.GET)
    public String customerList(Model model) {
        return "crm/customerinfo_list";
    }
	
	/**
	 * 表格
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/customerinfo/datagrid", method = RequestMethod.POST)
	public DataGrid datagrid(PageHelper page,CustomerInfo customerInfo) {
		DataGrid dg = new DataGrid();
		dg.setTotal(customerInfoService.getDatagridTotal(customerInfo));
		List<CustomerInfoPojo> list = customerInfoService.datagridCustomer(page);
		System.out.println(list.get(0).getDatetime().getTime());
		dg.setRows(list);
		return dg;
	}
	
	/**
	 * 新增
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerinfo/add",method = RequestMethod.POST)
    public Json add(Customer customer) {
		Json j = new Json();
		
		try {
			customerInfoService.addCustomer(customer);
            j.setSuccess(true);
            j.setMsg("用户新增成功！");
            j.setObj(customer);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	/**
     * 修改
     * 
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/customerinfo/edit",method = RequestMethod.POST)
    public Json editUser(Customer customer) {
        Json j = new Json();
        log.debug("穿过来的用户ID为："+customer.getId());
        try {
        	customerInfoService.editCustomer(customer);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
            j.setObj(customer);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	/**
	 * 删除某个
	 * @param out
	 */
	@ResponseBody
	@RequestMapping(value = "/customerinfo/delete",method = RequestMethod.POST)
	public Json delete(Customer customer) {
		Json j = new Json();
        log.debug("穿过来的用户ID为："+customer.getId());
        try {
        	customerInfoService.deleteCustomer(customer.getId());
			j.setSuccess(true);
	        j.setMsg("删除成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
	}
}
