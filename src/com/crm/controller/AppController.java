package com.crm.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.User;
import com.crm.pojo.AppJson;
import com.crm.service.CustomerInfoService;
import com.crm.service.UserService;
@Controller
@RequestMapping(value="/app")
public class AppController {
	@Resource
	UserService userService;
	@Resource
	CustomerInfoService customerInfoService;
	
	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value="/loginApp",method = RequestMethod.GET)
	public AppJson loginApp(User user){
		User userDb=userService.findUserByName(user.getUsername());
		AppJson json=new AppJson();
		if(userDb!=null&&userDb.getPassword()!=null
				&&userDb.getPassword()!=""
				&&userDb.getPassword().length()>0
				&&userDb.getPassword().equals(user.getPassword())){
			json.setSuccess(true);
			json.setMsg("登录成功");
			json.setObj(userDb);
		}else{
			json.setSuccess(false);
			json.setMsg("登录失败");
		}
		return json;
	}
	/**
	 * 修改密码
	 */
	@ResponseBody
	@RequestMapping(value="/changePwd",method = RequestMethod.GET)
	public AppJson changePwd(User user){
		AppJson json=new AppJson();
		try{
			userService.edit(user);
			json.setSuccess(true);
			json.setMsg("修改成功");
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg("修改失败");
		}
		return json;
	}
	/**
	 *县分下 客户信息列表
	 */
	@ResponseBody
	@RequestMapping(value="/customerInfoListByDistrictId",method = RequestMethod.GET)
	public AppJson customerInfoListByDistrictId(int district_id,int pageIndex){
		AppJson json=new AppJson();
		return json;
	}
	/**
	 *用户下 客户信息列表
	 */
	@RequestMapping(value="/customerInfoListByUserId")
	public void customerInfoListByUserId(){
		
	}
	/**
	 *添加
	 */
	@RequestMapping(value="/addCustomerInfo")
	public void addCustomerInfo(){
		
	}
	/**
	 *删除
	 */
	@RequestMapping(value="/delCustomerInfoByUserId")
	public void delCustomerInfoByUserId(){
		
	}
	/**
	 *修改
	 */
	@RequestMapping(value="/updateCustomerInfoByUserId")
	public void updateCustomerInfoByUserId(){
		
	}
}
