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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.District;
import com.crm.model.User;
import com.crm.model.easyui.DataGrid;
import com.crm.model.easyui.Json;
import com.crm.model.easyui.PageHelper;
import com.crm.model.easyui.Tree;
import com.crm.service.DistrictService;

/**
 * @author zh
 * 2014-8-23
 */
@Controller
@RequestMapping(value = "/district")
public class DistrictController {
	
	private final Logger log = LoggerFactory.getLogger(DistrictController.class);
	
	@Resource
	private DistrictService districtService;
	
	/**
	 * 跳转到县分页面
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
    public String districtList(Model model) {
        return "district/district";
    }
	/**
	 * 跳转到县分页面
	 * @return
	 */
	@RequestMapping(value = "/user_list",method = RequestMethod.GET)
    public String districtUserList(Model model) {
        return "district/district_user_tree";
    }
	/**
	 * 县分表格
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	public DataGrid datagrid(PageHelper page) {
		DataGrid dg = new DataGrid();
		dg.setTotal(districtService.datagridTotal());
		List<District> districtList = districtService.datagridDistrict(page);
		dg.setRows(districtList);
		return dg;
	}
	
	/**
	 * 新增县分
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addDistrict",method = RequestMethod.POST)
    public Json addDistrict(District district) {
		Json j = new Json();
		try {
			districtService.addDistrict(district);
            j.setSuccess(true);
            j.setMsg("用户新增成功！");
            j.setObj(district);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	/**
     * 修改县分
     * 
     * @param user
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/editDistrict",method = RequestMethod.POST)
    public Json editUser(District district) {
        Json j = new Json();
        log.debug("穿过来的用户ID为："+district.getId());
        try {
        	districtService.upDateDistrict(district);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
            j.setObj(district);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }
	
	/**
	 * 删除某个县分
	 * @param userId
	 * @param out
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteDistrict",method = RequestMethod.POST)
	public Json deleteDistrict(District district) {
		Json j = new Json();
        try {
			districtService.deleteDistrict(district.getId());
			j.setSuccess(true);
	        j.setMsg("删除成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
	}
	/**
	 * 县分节点树
	 */
	@ResponseBody
	@RequestMapping(value = "/nodesDistrict",method = RequestMethod.POST)
	public List<Tree> nodesDistrict() {
		List<Tree> list=null;
        try {
        	list=districtService.nodesDistrict();
        } catch (Exception e) {
        }
        return list;
	}
	
}
