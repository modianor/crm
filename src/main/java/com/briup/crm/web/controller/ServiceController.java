package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstService;
import com.briup.crm.bean.SysUser;
import com.briup.crm.service.ServeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/service")
public class ServiceController {
	
	@Autowired
	ServeService serveService;
	
	//根据经理id查找service
	@RequestMapping("/findServiceByUserName/{curPage}")
	public String findServiceByUserName(@PathVariable int curPage, HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		String userName = user.getUsrName();
		PageInfo<CstService> info = serveService.findServiceByUserName(curPage, 5, userName);
		session.setAttribute("serviceInfo", info);
		return "service/service";
	}
	
	//模糊查询
	@RequestMapping("/findServiceByCustNameAndType/{curPage}")
	public String findServiceByCustNameAndType(@PathVariable int curPage, CstService service, HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		String userName = user.getUsrName();
		service.setSvrDispose(userName);
		PageInfo<CstService> info = serveService.findServiceByCustNameAndType(curPage, 5, service);
		session.setAttribute("serviceInfo", info);
		return "service/service";
	}
	
	//新增和更新
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(CstService service, HttpSession session) {
		
		serveService.saveOrUpdate(service);
		
		return "forward:/service/findServiceByUserName/1" ;
	}
	
	//根据serId查找service
	@RequestMapping("/findServiceById/{svrId}")
	@ResponseBody
	public CstService findServiceById(@PathVariable Long svrId) {
		CstService service = serveService.findServiceById(svrId);
		return service;
	}
	
	//更新状态为已处理
	@RequestMapping("/updateStatusById/{svrId}")
	public String updateStatusById(@PathVariable Long svrId) {
		serveService.updateStatusById(svrId);
		return "forward:/service/findServiceByUserName/1";
	}
	
	//根据svrId查找service
	@RequestMapping("/findServiceDetailById/{svrId}")
	public String findServiceById(@PathVariable Long svrId, HttpSession session) {
		CstService service = serveService.findServiceById(svrId);
		session.setAttribute("service", service);
		return "service/serviceDetail2";
	}
	
	//查询所有service
	@RequestMapping("/findAllService/{curPage}")
	public String findAllService(@PathVariable int curPage, HttpSession session) {
		PageInfo<CstService> info = serveService.findAllService(curPage, 5);
		session.setAttribute("services", info);
		return "service/feedback";
	}
	
	//提交服务反馈结果
	@RequestMapping("/updateServiceWithFeedback")
	public String updateServiceWithFeedback(CstService service) {
		serveService.updateServiceWithFeedback(service);
		return "forward:/service/findAllService/1";
	}
	
	//根据type和status查询服务
	@RequestMapping("/findServiceByTypeAndStatus/{curPage}")
	public String findServiceByTypeAndStatus(@PathVariable int curPage, HttpSession session, CstService service) {
		PageInfo<CstService> info = serveService.findServiceByTypeAndStatus(curPage, 5, service);
		session.setAttribute("services", info);
		return "service/feedback";
	}
	
	//根据已反馈id查找服务信息
	@RequestMapping("/findDetailById2/{svrId}")
	
	public String findDetailById2(@PathVariable long svrId, HttpSession session) {
		CstService service = serveService.findServiceById(svrId);
		session.setAttribute("service", service);
		return "service/serviceDetail";
	}
}
