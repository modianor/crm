package com.briup.crm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstCustomer;
import com.briup.crm.service.CustomerService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	//分页显示所有顾客信息
	@RequestMapping("/findAllCustomerByPage/{curPage}")
	public String findAllCustomerByPage(@PathVariable("curPage") int curPage, HttpServletRequest request) {

		PageInfo<CstCustomer> customerInfo = customerService.findAllCustomerByPage(curPage, 5);
		request.setAttribute("customerInfo", customerInfo);
		
		return "customer/customer";
	}
	
	//保存客户
	@RequestMapping("/saveOrUpdateCustomer")
	@ResponseBody
	public String savaOrUpdateCustomer(CstCustomer customer) {
		customerService.saveOrUpdateCustomer(customer);
		return "保存成功";
	}
	
	//删除客户
	@RequestMapping("/deleteCustomerById/{custId}")
	@ResponseBody
	public String deleteCustomerById(@PathVariable long custId) {
		customerService.deleteCustomerById(custId);
		return "删除成功";
	}
	
	//根据id查找顾客
	@RequestMapping("/findCustoemrById/{custId}")
	@ResponseBody
	public CstCustomer findCustomerById(@PathVariable long custId) {
		CstCustomer customer = customerService.findCustomerById(custId);
		return customer;
	}
	
	
	@RequestMapping("/findCustomerLike/{curPage}")
	public String findCustomerLike(@PathVariable int curPage, CstCustomer customer, HttpServletRequest request) {
		PageInfo<CstCustomer> customerInfo = customerService.findCustomerLike(curPage, 5, customer);
		request.setAttribute("customerInfo", customerInfo);
		return "customer/customer";
	}
}
