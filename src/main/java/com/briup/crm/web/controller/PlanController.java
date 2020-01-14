package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping("/findPlansByUserId/{curPage}")
	public String findPlansByUserId(@PathVariable int curPage, HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		String dueTo = user.getUsrName();
		PageInfo<SalChance> chanceInfo =
		chanceService.findChanceByUserId(curPage, 5, dueTo);
		session.setAttribute("chanceInfo", chanceInfo);
		return "sales/plans";
	}
	
	////根据经理名和地址查找chance
	@RequestMapping("/findChanceByUserNameAndAddr/{curPage}")
	public String findChanceByUserNameAndAddr(@PathVariable int curPage, String addr, HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		String usrName = user.getUsrName();
		PageInfo<SalChance> info = chanceService.findChanceByUserNameAndAddr(curPage, 5, usrName, addr);
		System.out.println(info);
		session.setAttribute("chanceInfo", info);
		return "sales/plans";
	}
	
	//增加
	@RequestMapping("/toPlanAdd/{chcId}")
	public String toPlanAdd(@PathVariable Long chcId, HttpSession session) {
		session.setAttribute("chcId", chcId);
		SalChance chance = chanceService.findChanceById(chcId);
		session.setAttribute("chance", chance);
		return "sales/plan_add";
	}
	
	//保存
	@RequestMapping("/addPlan")
	public String addPlan(SalPlan plan, HttpSession session) {
		Long chcId = (Long)session.getAttribute("chcId");
		planService.savePlan(plan, chcId);
		return "forward:/plan/findPlansByUserId/1";
	}
	
	//编辑
	@RequestMapping("/toPlanEdit/{chcId}")
	public String toPlanEdit(@PathVariable Long chcId, HttpSession session) {
		session.setAttribute("chcId", chcId);
		SalChance chance = chanceService.findChanceById(chcId);
		session.setAttribute("chance", chance);
		SalChanceExtend chanceExtend = chanceService.findChanceWithPlanWithId(chcId);
		session.setAttribute("chanceExtend", chanceExtend);
		return "sales/plan_edit";
	}
	
	//根据plaId查找Plan
	@RequestMapping("/findPlanById/{plaId}")
	@ResponseBody
	public SalPlan  findPlanById(@PathVariable Long plaId) {
		SalPlan plan = planService.findPlanById(plaId);
		return plan;
	}
	
	//添加或者修改
	@RequestMapping("/addOrUpdate")
	public String addOrUpdate(SalPlan plan, HttpSession session) {
		Long chcId = (Long)session.getAttribute("chcId");
		System.out.println("chcID" + chcId);
		planService.addOrUpdatePlan(plan, chcId);
		return "forward:/plan/toPlanEdit/" + chcId;
	}
	
	//根据plaId删除plan
	@RequestMapping("/deletePlan/{plaId}")
	public String deletePlan(@PathVariable Long plaId, HttpSession session) {
		Long chcId = (Long)session.getAttribute("chcId");
		planService.deletePlanById(plaId);
		return "forward:/plan/toPlanEdit/" + chcId;
	}
	
	//详情
	@RequestMapping("/toPlanDetail/{chcId}")
	public String toPlanDetail(@PathVariable long chcId, HttpSession session) {
		SalChanceExtend chanceExtend = chanceService.findChanceWithPlanWithId(chcId);
		session.setAttribute("chanceExtend", chanceExtend);
		return "sales/plan_detail";
	}
	
	//开发成功
	@RequestMapping("/chanceSuccess")
	public String chanceSuccess(HttpSession session) {
		Long chcId = (Long)session.getAttribute("chcId");
		planService.updateChanceById(chcId);
		return "forward:/plan/findPlansByUserId/1";
	}
}
