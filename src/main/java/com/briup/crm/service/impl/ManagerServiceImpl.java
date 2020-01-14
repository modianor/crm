package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SysRole;
import com.briup.crm.bean.SysRoleExample;
import com.briup.crm.bean.SysUser;
import com.briup.crm.bean.SysUserExample;
import com.briup.crm.dao.SysRoleMapper;
import com.briup.crm.dao.SysUserMapper;
import com.briup.crm.service.ManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	private SysRoleMapper roleMapper;
	
	@Autowired
	private SysUserMapper userMapper;
	
	//查询所有角色
	@Override
	public PageInfo<SysRole> findAllRole(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		SysRoleExample example = new SysRoleExample();
		List<SysRole> list = roleMapper.selectByExample(example);
		PageInfo<SysRole> info = new PageInfo<>(list);
		return info;
	}

	//保存或修改role
	@Override
	public void saveOrUpdate(SysRole role) {
		if(role.getRoleId() == null) {
			roleMapper.insert(role);
		} else {
			roleMapper.updateByPrimaryKey(role);
		}
	}

	//根据id查找role
	@Override
	public SysRole findRoleById(Long id) {
		SysRole role = roleMapper.selectByPrimaryKey(id);
		return role;
	}

	//根据id删除role
	@Override
	public void deleteRoleById(Long id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	//查找所有user
	@Override
	public PageInfo<SysUser> findAllUser(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		SysUserExample example = new SysUserExample();
		List<SysUser> list = userMapper.selectByExample(example);
		
		PageInfo<SysUser> info = new PageInfo<>(list);
		return info;
	}

	//添加或修改user
	@Override
	public void saveOrUpdate(SysUser user) {
		SysRoleExample example = new SysRoleExample();
		example.createCriteria().andRoleNameEqualTo(user.getUsrRoleName());
		List<SysRole> list = roleMapper.selectByExample(example);
		Long roleId = list.get(0).getRoleId();
		user.setUsrRoleId(roleId);
		if(user.getUsrId() == null) {
			
			userMapper.insert(user);
		} else {
			userMapper.updateByPrimaryKey(user);
		}		
	}

	//根据id查找user
	@Override
	public SysUser findUserById(Long id) {
		SysUser user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	//根据删除user
	@Override
	public void deleteUserById(Long id) {
		userMapper.deleteByPrimaryKey(id);
	}

	//根据role类型查看user
	@Override
	public PageInfo<SysUser> findUserByRoleName(int curPage, int size, String roleName) {
		PageHelper.startPage(curPage, size);
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsrRoleNameLike("%" + roleName + "%");
		List<SysUser> list = userMapper.selectByExample(example);
		PageInfo<SysUser> info = new PageInfo<>(list);
		return info;
	}

}
