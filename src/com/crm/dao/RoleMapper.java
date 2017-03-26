/**
 * 
 */
package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.model.Role;
import com.crm.model.SysMenu;
import com.crm.model.User;
import com.crm.model.easyui.PageHelper;
import com.crm.pojo.UserPojo;

/**
 * @author zh
 * 2014-8-2
 */
public interface RoleMapper {
	/**
	 * 权限列表
	 * @return
	 */
	public List<Role> getRoleList();
	public void roleUserEdit(@Param("ids")List<Integer>ids,@Param("roleId")Integer roleId);
}
