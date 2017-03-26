/**
 * 
 */
package com.crm.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crm.dao.RoleMapper;
import com.crm.model.District;
import com.crm.model.Role;
import com.crm.model.easyui.Tree;

/**
 * @author zh
 * 2014-8-2
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
public class RoleService {

	@Resource
	private RoleMapper roleMapper;
	/**
	 * 权限列表
	 */
	public List<Tree> nodesRole(){
		List<Role> list=roleMapper.getRoleList();
		List<Tree> treeList=null;
		if(list!=null){
			treeList = new ArrayList<Tree>();
			for(Role dis:list){
				Tree node = new Tree();				
				node.setId((int)dis.getId());
				node.setPid(0);
				node.setText(dis.getRoleName());
				node.setIconCls(null);				
				treeList.add(node);
			}
			Tree node = new Tree();				
			node.setId(9999);
			node.setPid(0);
			node.setText("空");
			node.setIconCls(null);				
			treeList.add(node);
			Tree node1 = new Tree();				
			node1.setId(0);
			node1.setPid(-1);
			node1.setText("全部");
			node1.setIconCls(null);				
			treeList.add(node1);
		}
		return treeList;
	}
    public void roleUserEdit(List<Integer>ids,Integer roleId){
    	roleMapper.roleUserEdit(ids, roleId);
    }
}
