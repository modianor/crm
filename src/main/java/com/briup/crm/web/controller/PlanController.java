package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalPlan;
import com.briup.crm.bean.SysUser;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.briup.crm.service.PlanService;
import com.briup.crm.service.SaleChanceService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/plan")
public class PlanController {
	@Autowired
	private SaleChanceService chanceService;
	
	@Autowired
	private PlanService planService;
	
	//根据登录用户名查找chance
	@ApiOperation(value = "根据当前页和用户ID查找计划", notes = "分页查找计划", httpMethod = "GET")
	@RequestMapping(value = "/findPlansByUserId/{curPage}", method = RequestMethod.GET)
	public String findPlansByUserId(@PathVariable int curPage, HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		String dueTo = user.getUsrName();
		PageInfo<SalChance> chanceInfo =
		chanceService.findChanceByUserId(curPage, 5, dueTo);
		session.setAttribute("chanceInfo", chanceInfo);
		return "sales/plans";
	}
	
	////根据经理名和地址查找chance
	@ApiOperation(value = "根据当前页 经理名称 地址查找商机", notes = "分页查找商机", httpMethod = "GET")
	@RequestMapping(value = "/findChanceByUserNameAndAddr/{curPage}", method = RequestMethod.GET)
	public String findChanceByUserNameAndAddr(@PathVariable int curPage, String addr, HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		String usrName = user.getUsrName();
		PageInfo<SalChance> info = chanceService.findChanceByUserNameAndAddr(curPage, 5, usrName, addr);
		System.out.println(info);
		session.setAttribute("chanceInfo", info);
		return "sales/plans";
	}
	
	//增加
	@ApiOperation(value = "根据商机ID增加计划", notes = "增加计划", httpMethod = "GET")
	@RequestMapping(value = "/toPlanAdd/{chcId}", method = RequestMethod.GET)
	public String toPlanAdd(@PathVariable Long chcId, HttpSession session) {
		session.setAttribute("chcId", chcId);
		SalChance chance = chanceService.findChanceById(chcId);
		session.setAttribute("chance", chance);
		return "sales/plan_add";
	}
	
	//保存
	@ApiOperation(value = "保存计划", notes = "保存计划", httpMethod = "GET")
	@RequestMapping(value = "/addPlan", method = RequestMethod.GET)
	public String addPlan(SalPlan plan, HttpSession session) {
		Long chcId = (Long)session.getAttribute("chcId");
		planService.savePlan(plan, chcId);
		return "forward:/plan/findPlansByUserId/1";
	}
	
	//编辑
	@ApiOperation(value = "根据商机ID查找商机扩展并编辑", notes = "编辑计划", httpMethod = "GET")
	@RequestMapping(value = "/toPlanEdit/{chcId}", method = RequestMethod.GET)
	public String toPlanEdit(@PathVariable Long chcId, HttpSession session) {
		session.setAttribute("chcId", chcId);
		SalChance chance = chanceService.findChanceById(chcId);
		session.setAttribute("chance", chance);
		SalChanceExtend chanceExtend = chanceService.findChanceWithPlanWithId(chcId);
		session.setAttribute("chanceExtend", chanceExtend);
		return "sales/plan_edit";
	}


	//根据plaId查找Plan
	@ApiOperation(value = "根据计划ID查找计划", notes = "查找计划", httpMethod = "GET")
	@RequestMapping(value = "/findPlanById/{plaId}", method = RequestMethod.GET)
	@ResponseBody
	public SalPlan  findPlanById(@PathVariable Long plaId) {
		SalPlan plan = planService.findPlanById(plaId);
		return plan;
	}
	
	//添加或者修改
	@ApiOperation(value = "增加计划", notes = "增加计划", httpMethod = "GET")
	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.GET)
	public String addOrUpdate(SalPlan plan, HttpSession session) {
		Long chcId = (Long)session.getAttribute("chcId");
		System.out.println("chcID" + chcId);
		planService.addOrUpdatePlan(plan, chcId);
		return "forward:/plan/toPlanEdit/" + chcId;
	}
	
	//根据plaId删除plan
	@ApiOperation(value = "删除计划", notes = "删除角色", httpMethod = "GET")
	@RequestMapping(value = "/deletePlan/{plaId}", method = RequestMethod.GET)
	public String deletePlan(@PathVariable Long plaId, HttpSession session) {
		Long chcId = (Long)session.getAttribute("chcId");
		planService.deletePlanById(plaId);
		return "forward:/plan/toPlanEdit/" + chcId;
	}
	
	//详情
	@ApiOperation(value = "根据商机ID查找商机并编辑", notes = "编辑商机", httpMethod = "GET")
	@RequestMapping(value = "/toPlanDetail/{chcId}", method = RequestMethod.GET)
	public String toPlanDetail(@PathVariable long chcId, HttpSession session) {
		SalChanceExtend chanceExtend = chanceService.findChanceWithPlanWithId(chcId);
		session.setAttribute("chanceExtend", chanceExtend);
		return "sales/plan_detail";
	}
	
	//开发成功
	@ApiOperation(value = "商机成功并更新商机信息", notes = "更新商机", httpMethod = "GET")
	@RequestMapping(value = "/chanceSuccess", method = RequestMethod.GET)
	public String chanceSuccess(HttpSession session) {
		Long chcId = (Long)session.getAttribute("chcId");
		planService.updateChanceById(chcId);
		return "forward:/plan/findPlansByUserId/1";
	}
}
