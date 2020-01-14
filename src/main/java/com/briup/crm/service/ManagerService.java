package com.briup.crm.service;

import com.briup.crm.bean.SysRole;
import com.briup.crm.bean.SysUser;
import com.github.pagehelper.PageInfo;

public interface ManagerService {
	//查询所有角色role
	public PageInfo<SysRole> findAllRole(int curPage, int size);
	
	//添加或修改role
	public void saveOrUpdate(SysRole role);
	
	//根据id查找role
	public SysRole findRoleById(Long id);
	
	//根据id删除role
	public void deleteRoleById(Long id);
	
	//查询所有角色user
	public PageInfo<SysUser> findAllUser(int curPage, int size);
	
	//添加或修改user
	public void saveOrUpdate(SysUser user);
	
	//根据id查找user
	public SysUser findUserById(Long id);
	
	//根据id删除user
	public void deleteUserById(Long id);
	
	//根据role类型查看user
	public PageInfo<SysUser> findUserByRoleName(int curPage, int size, String roleName);
}
