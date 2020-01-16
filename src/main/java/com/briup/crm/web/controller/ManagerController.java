package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @ApiOperation(value = "根据当前页查找所有角色", notes = "查找角色", httpMethod = "GET")
    @RequestMapping(value = "/toRole/{curPage}", method = RequestMethod.GET)
    public String toRole(HttpSession session, @PathVariable int curPage) {
        PageInfo<SysRole> info = managerService.findAllRole(curPage, 5);
        session.setAttribute("roleInfo", info);
        return "manager/role";
    }

    //保存或修改
    @ApiOperation(value = "保存或者更新角色", notes = "保存或更新", httpMethod = "GET")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.GET)
    public String saveOrUpdate(SysRole role) {
        managerService.saveOrUpdate(role);
        return "forward:/manager/toRole/1";
    }

    //根据id查找role
    @ApiOperation(value = "根据角色ID查找角色", notes = "查找角色", httpMethod = "GET")
    @RequestMapping(value = "findRoleById/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public SysRole findRoleById(@PathVariable Long roleId) {
        SysRole role = managerService.findRoleById(roleId);
        return role;
    }

    //根据id删除role
    @ApiOperation(value = "根据角色ID删除角色", notes = "删除角色", httpMethod = "GET")
    @RequestMapping(value = "/deleteRoleById/{roleId}", method = RequestMethod.GET)
    public String deleteRoleById(@PathVariable long roleId) {
        managerService.deleteRoleById(roleId);
        return "forward:/manager/toRole/1";
    }


    //跳转到user.jsp
    @ApiOperation(value = "根据当前页查找系统用户信息", notes = "查找分页信息", httpMethod = "GET")
    @RequestMapping(value = "/toUser/{curPage}", method = RequestMethod.GET)
    public String toUser(HttpSession session, @PathVariable int curPage) {
        PageInfo<SysUser> info = managerService.findAllUser(curPage, 5);
        session.setAttribute("userInfo", info);
        return "manager/user";
    }

    //保存或修改user
    @ApiOperation(value = "保存或者更新用户", notes = "保存或更新", httpMethod = "GET")
    @RequestMapping(value = "/saveOrUpdateUser", method = RequestMethod.GET)
    public String saveOrUpdateUser(SysUser user) {
        managerService.saveOrUpdate(user);
        return "forward:/manager/toUser/1";
    }

    //根据id查找user
    @ApiOperation(value = "根据用户ID查找用户", notes = "查找用户", httpMethod = "GET")
    @RequestMapping(value = "findUserById/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public SysUser findUserById(@PathVariable Long userId) {
        SysUser user = managerService.findUserById(userId);
        return user;
    }

    //根据id删除user
    @ApiOperation(value = "根据用户ID删除用户", notes = "删除用户", httpMethod = "GET")
    @RequestMapping(value = "/deleteUserById/{userId}", method = RequestMethod.GET)
    public String deleteUserById(@PathVariable long userId) {
        managerService.deleteUserById(userId);
        return "forward:/manager/toUser/1";
    }

    //根据roleName查找user
    @ApiOperation(value = "根据当前页和用户角色名称查找用户", notes = "分页查找", httpMethod = "GET")
    @RequestMapping(value = "/findUserByRoleName/{curPage}", method = RequestMethod.GET)
    public String findUserByRoleName(@PathVariable int curPage, String usrRoleName, HttpSession session) {
        PageInfo<SysUser> info = managerService.findUserByRoleName(curPage, 5, usrRoleName);
        session.setAttribute("userInfo", info);
        return "manager/user";
    }

}
