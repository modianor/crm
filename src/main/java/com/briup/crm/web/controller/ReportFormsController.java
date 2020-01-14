package com.briup.crm.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.Contribution;
import com.briup.crm.service.ConstituteService;
import com.briup.crm.service.ContributionService;

@Controller
@RequestMapping("reportForms")
public class ReportFormsController {
	@Autowired
	private ContributionService contributionService;
	@Autowired
	private ConstituteService conService;
	
	//跳转到贡献分析
	@RequestMapping("/toContribution")
	public String toContribution() {
		return "reportForms/contribution";
	}
	
	//用户贡献
	@RequestMapping("/getContribution")
	@ResponseBody
	public List<Contribution> getContribution() {
		List<Contribution> list = contributionService.findContribution();
		System.out.println(list);
		return list;
	}
	
	//根据region查询贡献
	@RequestMapping("/getContributionByRegion")
	@ResponseBody
	public List<Contribution> getContributionByRegion(String region) {
		Contribution con = contributionService.findContributionByRegion(region);
		List<Contribution> c = new ArrayList<>();
		c.add(con);
		return c;
	}
	
	//跳转用户构成分析页面
	@RequestMapping("/toConstitute")
	public String toConstitute() {
		return "reportForms/constitute";
	}
	
	//用户构成
	@RequestMapping("/getConstitute")
	@ResponseBody
	public List<Contribution> getConstitute(int condition) {
		List<Contribution> list = conService.findCustMarkup(condition);
		return list;
	}
}
