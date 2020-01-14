package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstLinkman;
import com.briup.crm.service.LinkmanService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/linkman")
public class LinkmanController {
	@Autowired
	private LinkmanService linkmanService;
	
	//根据custId查找联系人
	@RequestMapping("/findLinkmanByCustId/{custId}/{curPage}")
	public String findLinkmanByCustId(@PathVariable("custId") long custId, @PathVariable("curPage") int curPage, HttpSession session) {
		PageInfo<CstLinkman> pageInfo = linkmanService.findLinkmanByCustId(custId, curPage, 5);
		session.setAttribute("linkmanInfo", pageInfo);
		session.setAttribute("custId", custId);
		return "customer/linkman";
	}
	
	//根据联系人id删除联系人
	@RequestMapping("/deleteLinkmanById/{lmkId}")

	public String deleteLinkmanById(@PathVariable long lmkId, HttpSession session) {
		linkmanService.deleteLinkmanById(lmkId);
		long custId = (long)session.getAttribute("custId");
		
		return "forward:/linkman/findLinkmanByCustId/" + custId + "/1";
	}
	
	//添加或修改联系人
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(CstLinkman linkman, HttpSession session) {
		Long custId = (Long)session.getAttribute("custId");
		linkman.setLkmCustId(custId);
		linkmanService.saveOrUpdate(linkman);
		return "forward:/linkman/findLinkmanByCustId/" + custId + "/1";
	}
	
	@RequestMapping("/findLinkmanById/{lkmId}")
	@ResponseBody
	public CstLinkman findLinkmanById(@PathVariable Long lkmId) {
		CstLinkman linkman = linkmanService.findLinkmanById(lkmId);
		return linkman;
	}
}
