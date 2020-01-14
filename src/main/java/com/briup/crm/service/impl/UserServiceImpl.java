package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SysUser;
import com.briup.crm.bean.SysUserExample;
import com.briup.crm.dao.SysUserMapper;

import com.briup.crm.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private SysUserMapper userMapper;
	@Override
	public SysUser login(String name, String password) throws Exception {
		//查询用户是否存在
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsrNameEqualTo(name);
		List<SysUser> userList = userMapper.selectByExample(example);
		if(userList.size()>0) {
			SysUser user = userList.get(0);
			if(user.getUsrPassword().equals(password)) {
				return user;
			}else {
				throw new Exception("密码错误，重新输入");
			}
		}else {
			throw new Exception("用户名错误，重新输入");
		}
		
	}
}