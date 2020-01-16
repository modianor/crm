package com.briup.crm.web.controller;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @ApiOperation(value = "根据当前页查询所有客户", notes = "查找分页信息", httpMethod = "GET")
    @RequestMapping(value = "/findAllCustomerByPage/{curPage}", method = RequestMethod.GET)
    public String findAllCustomerByPage(@ApiParam(value = "当前页",example = "1") @PathVariable("curPage") int curPage, HttpServletRequest request) {

        PageInfo<CstCustomer> customerInfo = customerService.findAllCustomerByPage(curPage, 5);
        request.setAttribute("customerInfo", customerInfo);

        return "customer/customer";
    }

    //保存客户
    @ApiOperation(value = "保存或者更新客户", notes = "保存或更新", httpMethod = "POST")
    @RequestMapping(value = "/saveOrUpdateCustomer", method = RequestMethod.POST)
    @ResponseBody
    public String savaOrUpdateCustomer(@ApiParam(value = "客户实体类") CstCustomer customer) {
        customerService.saveOrUpdateCustomer(customer);
        return "保存成功";
    }

    //删除客户
    @ApiOperation(value = "根据客户ID删除客户", notes = "删除客户", httpMethod = "GET")
    @RequestMapping(value = "/deleteCustomerById/{custId}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteCustomerById(@ApiParam(value = "客户ID",example = "1") @PathVariable long custId) {
        customerService.deleteCustomerById(custId);
        return "删除成功";
    }

    //根据id查找顾客
    @ApiOperation(value = "根据客户ID查找客户", notes = "查找客户", httpMethod = "GET")
    @RequestMapping(value = "/findCustoemrById/{custId}", method = RequestMethod.GET)
    @ResponseBody
    public CstCustomer findCustomerById(@ApiParam(value = "客户ID",example = "1") @PathVariable long custId) {
        CstCustomer customer = customerService.findCustomerById(custId);
        return customer;
    }

    @ApiOperation(value = "根据当前页和客户信息进行模糊查找", notes = "模糊查找分页信息", httpMethod = "GET")
    @RequestMapping(value = "/findCustomerLike/{curPage}", method = RequestMethod.GET)
    public String findCustomerLike(@ApiParam(value = "当前页",example = "1") @PathVariable int curPage, @ApiParam(value = "客户实体类") CstCustomer customer, HttpServletRequest request) {
        PageInfo<CstCustomer> customerInfo = customerService.findCustomerLike(curPage, 5, customer);
        request.setAttribute("customerInfo", customerInfo);
        return "customer/customer";
    }
}
