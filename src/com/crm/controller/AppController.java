package com.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.CustomerInfo;
import com.crm.model.User;
import com.crm.model.easyui.PageHelper;
import com.crm.pojo.AppJson;
import com.crm.pojo.CustomerInfoPojo;
import com.crm.service.CustomerInfoService;
import com.crm.service.UserService;

@Controller
@RequestMapping(value = "/app")
public class AppController {
	@Resource
	UserService userService;
	@Resource
	CustomerInfoService customerInfoService;

	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "/loginApp", method = RequestMethod.GET)
	public AppJson loginApp(User user) {
		User userDb = userService.findUserByName(user.getUsername());
		AppJson json = new AppJson();
		if (userDb != null && userDb.getPassword() != null
				&& userDb.getPassword() != ""
				&& userDb.getPassword().length() > 0
				&& userDb.getPassword().equals(user.getPassword())) {
			json.setSuccess(true);
			json.setMsg("登录成功");
			json.setObj(userDb);
		} else {
			json.setSuccess(false);
			json.setMsg("登录失败");
		}
		return json;
	}

	/**
	 * 修改密码
	 */
	@ResponseBody
	@RequestMapping(value = "/changePwd", method = RequestMethod.GET)
	public AppJson changePwd(User user) {
		AppJson json = new AppJson();
		try {
			userService.edit(user);
			json.setSuccess(true);
			json.setMsg("修改成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("修改失败");
		}
		return json;
	}

	/**
	 * 县分下 客户信息列表
	 */
	@ResponseBody
	@RequestMapping(value = "/customerInfoListByDistrictId", method = RequestMethod.GET)
	public AppJson customerInfoListByDistrictId(PageHelper pageHelper,
			CustomerInfoPojo customerInfo) {
		AppJson json = new AppJson();
		pageHelper.setRows(10);
		pageHelper.setOrder("datetime");
		pageHelper.setSort("desc");
		try {
			json.setSuccess(true);
			Long totalCount = customerInfoService
					.getDatagridTotal(customerInfo);
			json.setPageCount((int) Math.ceil(totalCount / 10.0));
			List<CustomerInfoPojo> list = customerInfoService.datagridCustomer(
					pageHelper, customerInfo);
			json.setObj(list);
		} catch (Exception e) {
			json.setSuccess(false);
		}
		return json;
	}

	/**
	 * 用户下 客户信息列表
	 */
	@ResponseBody
	@RequestMapping(value = "/customerInfoListByUserId", method = RequestMethod.GET)
	public AppJson customerInfoListByUserId(PageHelper pageHelper,
			CustomerInfoPojo customerInfo) {
		AppJson json = new AppJson();

		pageHelper.setRows(10);
		pageHelper.setOrder("datetime");
		pageHelper.setSort("desc");
		try {
			json.setSuccess(true);
			Long totalCount = customerInfoService
					.getDatagridTotal(customerInfo);
			json.setPageCount((int) Math.ceil(totalCount / 10.0));
			List<CustomerInfoPojo> list = customerInfoService.datagridCustomer(
					pageHelper, customerInfo);
			json.setObj(list);
		} catch (Exception e) {
			json.setSuccess(false);
		}
		return json;
	}

	/**
	 * 添加
	 */
	@ResponseBody
	@RequestMapping(value = "/addCustomerInfo", method = RequestMethod.POST)
	public AppJson addCustomerInfo(CustomerInfo customerInfo) {
		AppJson json = new AppJson();
		try {
			json.setSuccess(true);
			customerInfoService.addCustomerInfo(customerInfo);
		} catch (Exception e) {
			json.setSuccess(false);
		}
		return json;
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value = "/delCustomerInfoByUserId", method = RequestMethod.GET)
	public AppJson delCustomerInfoByUserId(int id) {
		AppJson json = new AppJson();
		try {
			json.setSuccess(true);
			customerInfoService.deleteCustomerInfo(id);;
		} catch (Exception e) {
			json.setSuccess(false);
		}
		return json;
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCustomerInfoByUserId", method = RequestMethod.POST)
	public AppJson updateCustomerInfoByUserId(CustomerInfo customerInfo) {
		AppJson json = new AppJson();
		try {
			json.setSuccess(true);
			customerInfoService.editCustomerInfo(customerInfo);
		} catch (Exception e) {
			json.setSuccess(false);
		}
		return json;
	}
}
