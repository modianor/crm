package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.SysRole;
import com.briup.crm.bean.SysUser;
import com.briup.crm.service.ManagerService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	private ManagerService managerService;
	
	//跳转到role.jsp
	@RequestMapping("/toRole/{curPage}")
	public String toRole(HttpSession session,@PathVariable int curPage) {
		 PageInfo<SysRole> info = managerService.findAllRole(curPage, 5);
		session.setAttribute("roleInfo", info);
		return "manager/role";
	}
	
	//保存或修改
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(SysRole role) {
		managerService.saveOrUpdate(role);
		return "forward:/manager/toRole/1";
	}
	
	//根据id查找role
	@RequestMapping("findRoleById/{roleId}")
	@ResponseBody
	public SysRole findRoleById(@PathVariable Long roleId) {
		SysRole role = managerService.findRoleById(roleId);
		return role;
	}
	
	//根据id删除role
	@RequestMapping("/deleteRoleById/{roleId}")
	public String deleteRoleById(@PathVariable long roleId) {
		managerService.deleteRoleById(roleId);
		return "forward:/manager/toRole/1";
	}
	
	
	//跳转到user.jsp
	@RequestMapping("/toUser/{curPage}")
	public String toUser(HttpSession session,@PathVariable int curPage) {
		 PageInfo<SysUser> info = managerService.findAllUser(curPage, 5);
		session.setAttribute("userInfo", info);
		return "manager/user";
	}
	
	//保存或修改user
	@RequestMapping("/saveOrUpdateUser")
	public String saveOrUpdateUser(SysUser user) {
		managerService.saveOrUpdate(user);
		return "forward:/manager/toUser/1";
	}
	
	//根据id查找user
	@RequestMapping("findUserById/{userId}")
	@ResponseBody
	public SysUser findUserById(@PathVariable Long userId) {
		SysUser user = managerService.findUserById(userId);
		return user;
	}
	
	//根据id删除user
	@RequestMapping("/deleteUserById/{userId}")
	public String deleteUserById(@PathVariable long userId) {
		managerService.deleteUserById(userId);
		return "forward:/manager/toUser/1";
	}
	
	//根据roleName查找user
	@RequestMapping("/findUserByRoleName/{curPage}")
	public String findUserByRoleName(@PathVariable int curPage, String usrRoleName, HttpSession session) {
		PageInfo<SysUser> info = managerService.findUserByRoleName(curPage, 5, usrRoleName);
		session.setAttribute("userInfo", info);
		return "manager/user";
	}
	
}
