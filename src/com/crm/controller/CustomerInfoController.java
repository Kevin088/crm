/**
 * 
 */
package com.crm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.Customer;
import com.crm.model.CustomerInfo;
import com.crm.model.User;
import com.crm.model.easyui.DataGrid;
import com.crm.model.easyui.Json;
import com.crm.model.easyui.PageHelper;
import com.crm.pojo.CustomerInfoPojo;
import com.crm.service.CustomerInfoService;
import com.crm.util.common.Const;

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
	public DataGrid datagrid(PageHelper page,CustomerInfoPojo customerInfoPojo) {
		DataGrid dg = new DataGrid();
		dg.setTotal(customerInfoService.getDatagridTotal(customerInfoPojo));
		List<CustomerInfoPojo> list = customerInfoService.datagridCustomer(page,customerInfoPojo);
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
    public Json add(CustomerInfo customerInfo,HttpServletRequest request) {
		Json j = new Json();		
		try {
			User user =  (User)request.getSession().getAttribute(Const.SESSION_USER); 
			customerInfo.setUsername_id(user.getId());
			customerInfoService.addCustomerInfo(customerInfo);
            j.setSuccess(true);
            j.setMsg("用户新增成功！");
            j.setObj(customerInfo);
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
    public Json editUser(CustomerInfo customerInfo) {
        Json j = new Json();
        log.debug("穿过来的编辑的customerInfo的ID为："+customerInfo.getId());
        try {
        	customerInfoService.editCustomerInfo(customerInfo);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
            j.setObj(customerInfo);
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
	public Json delete(CustomerInfo customerInfo) {
		Json j = new Json();
        log.debug("穿过来的用户ID为："+customerInfo.getId());
        try {
        	customerInfoService.deleteCustomerInfo(customerInfo.getId());
			j.setSuccess(true);
	        j.setMsg("删除成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
	}
}
