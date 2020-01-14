package com.briup.crm.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstCustomer;
import com.briup.crm.bean.CstCustomerExample;
import com.briup.crm.dao.CstCustomerMapper;
import com.briup.crm.dao.extend.CstCustomerExtendMapper;
import com.briup.crm.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class CustomerServiceImpl implements CustomerService{
	

	@Autowired
	CstCustomerMapper customerMapper;
	
	@Autowired
	CstCustomerExtendMapper mapper;
	
	@Override
	public List<CstCustomer> findAllCustomer() {
		CstCustomerExample example = new CstCustomerExample();
		List<CstCustomer> list = customerMapper.selectByExample(example);
		return list;
	}
	
	//分页显示所有用户信息
	@Override
	public PageInfo<CstCustomer> findAllCustomerByPage(int curPage, int size) {
		//设置当前是哪一页，以及每页显示几条数据
		PageHelper.startPage(curPage, size);
		//查询所有顾客信息
		CstCustomerExample example = new CstCustomerExample();
		List<CstCustomer> customerList = customerMapper.selectByExample(example);
		//将顾客信息传递给分页对象
		PageInfo<CstCustomer> customerInfo = new PageInfo<CstCustomer>(customerList);
		return customerInfo;
	}

	//保存customer
	@Override
	public void saveOrUpdateCustomer(CstCustomer customer) {
		//判断id是否为空，为空则是保存操作，否则更新
		if(customer.getCustId() == null) {
			customerMapper.insertSelective(customer);			
		} else {
			customerMapper.updateByPrimaryKey(customer);
		}
	}

	//删除客户
	@Override
	public void deleteCustomerById(long custId) {
		customerMapper.deleteByPrimaryKey(custId);
	}

	//根据ID查找顾客
	@Override
	public CstCustomer findCustomerById(long custId) {
		CstCustomer customer = customerMapper.selectByPrimaryKey(custId);
		return customer;
	}

	@Override
	public PageInfo<CstCustomer> findCustomerLike(int curPage, int size, CstCustomer customer) {
		//设置当前页，每页几条数据
		PageHelper.startPage(curPage, size);
		//模糊查询，根据客户名字，区域，等级
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustNameLike("%" + customer.getCustName() + "%")
		.andCustRegionLike("%" + customer.getCustRegion() + "%")
		.andCustLevelLabelLike("%" + customer.getCustLevelLabel() + "%");
		List<CstCustomer> customerList = customerMapper.selectByExample(example);
		//将查询出的数据传递给分页对象
		PageInfo<CstCustomer> customerInfo = new PageInfo<CstCustomer>(customerList);
		return customerInfo;
	}
	
	//获得总的消费金额
	public float getTotal() {
		float total = 0;
		//查询所有顾客，然后获取每个顾客的消费金额
		CstCustomerExample example = new CstCustomerExample();
		List<CstCustomer> list = customerMapper.selectByExample(example);
		for (CstCustomer cstCustomer : list) {
			total += cstCustomer.getCustTurnover();
		}
		System.out.println("总金额" + total);
		return total;
	}
	
	//获得每个区域的消费金额
	public float getRegionTotal(String region) {
		float total = 0;
		//查询每个区域的顾客
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustRegionEqualTo(region);
		List<CstCustomer> list = customerMapper.selectByExample(example);
		for (CstCustomer cstCustomer : list) {
			total += cstCustomer.getCustTurnover();
		}
		return total;
	}
	
	//查每个区域的贡献百分比
	@Override
	public float getRegionPercent(String region) {
		float sum = getRegionTotal(region);
		float total = getTotal();
		float per = sum/total;
		return per;
	}
	
	

	//查询不同等级的顾客人数
	@Override
	public int getCustByLevel(String level) {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustLevelLabelEqualTo(level);
		int l = (int)customerMapper.countByExample(example);
		return l;
	}

	//查询不同信誉度的顾客人数
	@Override
	public int getCustByCredit(int credit) {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustCreditEqualTo(credit);
		int l = (int)customerMapper.countByExample(example);
		return l;
	}

	//查询不同满意度的顾客人数
	@Override
	public int getCustBySatisfy(int satisfy) {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustSatisfyEqualTo(satisfy);
		int l = (int)customerMapper.countByExample(example);
		return l;
	}

	//查询所有的等级
	@Override
	public Set<String> findAllLevel() {
		Set<String> levelSet = new HashSet<>();
		List<CstCustomer> list = findAllCustomer();
		for (CstCustomer c : list) {
			levelSet.add(c.getCustLevelLabel());
		}
		return levelSet;
	}

	//查询所有信誉度
	@Override
	public Set<Integer> findAllCredit() {
		Set<Integer> creditSet = new HashSet<>();
		List<CstCustomer> list = findAllCustomer();
		for (CstCustomer c : list) {
			creditSet.add(c.getCustCredit());
		}
		return creditSet;
	}

	//查询所有满意度
	@Override
	public Set<Integer> findAllSatisfy() {
		Set<Integer> satisfySet = new HashSet<>();
		List<CstCustomer> list = findAllCustomer();
		for (CstCustomer c : list) {
			satisfySet.add(c.getCustSatisfy());
			
		}
		return satisfySet;
	}	
	

}
