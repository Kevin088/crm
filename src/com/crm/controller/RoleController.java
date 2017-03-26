package com.crm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.easyui.Tree;

@Controller
public class RoleController {
	/**
	 *  角色节点树
	 */
	@ResponseBody
	@RequestMapping(value = "/nodesDistrict",method = RequestMethod.POST)
	public List<Tree> nodesDistrict() {
		List<Tree> list=null;
        try {
        	//list=districtService.nodesDistrict();
        } catch (Exception e) {
        }
        return list;
	}
}
