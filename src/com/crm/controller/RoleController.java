package com.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.easyui.Json;
import com.crm.model.easyui.Tree;
import com.crm.service.RoleService;

@Controller
@RequestMapping(value="/role")
public class RoleController {
	@Resource
	RoleService roleService;
	/**
	 *  角色节点树 
	 */
	@RequestMapping(value = "/user_role")
	public String getView() {
        return "rolemanager/role_user_tree";
	}
	/**
	 *  角色节点树 
	 */
	@ResponseBody
	@RequestMapping(value = "/nodesrole",method = RequestMethod.POST)
	public List<Tree> nodesDistrict() {
		List<Tree> list=null;
        try {
        	list=roleService.nodesRole();
        } catch (Exception e) {
        }
        return list;
	}
	/**
	 *  角色节点树 
	 */
	@ResponseBody
	@RequestMapping(value = "/roleEdit",method = RequestMethod.POST)
	public Json roleUserEdit(@RequestParam("ids[]") List<Integer> ids,@RequestParam("roleId") Integer roleId) {
		
		Json j = new Json();	
		try {
            roleService.roleUserEdit(ids, roleId);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
            j.setObj("编辑成功");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
		if(ids.size()>0&&roleId>0){}else{
			j.setSuccess(false);
		}
        return j;
	}
}
