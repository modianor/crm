package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.SalChance;
import com.briup.crm.service.SaleChanceService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/chance")
public class SaleChanceController {
	@Autowired
	private SaleChanceService chanceService;
	
	//查询（分页）
	@RequestMapping("/findSalesChance/{curPage}")
	public String findSalesChance(@PathVariable int curPage, HttpSession session) {
		PageInfo<SalChance> pageInfo = chanceService.findSalChance(curPage, 5);
		session.setAttribute("chanceInfo", pageInfo);
		return "sales/sales";
	}
	
	
	@RequestMapping("/findSaleChanceLike/{curPage}")
	public String findSaleChanceLike(@PathVariable int curPage, String chcCustName, String chcAddr, HttpSession session) {
		
		PageInfo<SalChance> pageInfo = chanceService.findSaleChanceLike(curPage, 5, chcCustName, chcAddr);
		session.setAttribute("chanceInfo", pageInfo);
		return "sales/sales";
	}
	
	//保存
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(SalChance chance, HttpSession session) {
		chanceService.saveOrUpdate(chance);
		Long chcId = (long)session.getAttribute("chcId");
		return "forward:/chance/findSalesChance/1";
	}
	
	//查询
	@RequestMapping("/findChanceById/{chcId}")
	@ResponseBody
	public SalChance findChanceById(@PathVariable long chcId, HttpSession session) {
		SalChance chance = chanceService.findChanceById(chcId);
		session.setAttribute("chcId", chcId);
		return chance;
	}
	
	//删除
	@RequestMapping("/deleteChanceById/{chcId}")
	
	public String deleteChanceById(@PathVariable long chcId) {
		chanceService.deleteChanceById(chcId);
		return "forward:/chance/findSalesChance/" + chcId + "/1";
	}
}
